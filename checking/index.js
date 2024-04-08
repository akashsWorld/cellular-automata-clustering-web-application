const fs = require("fs");

const dataWithSync = fs.readFileSync("./dummy-db.json", "utf-8");

const jsonData = JSON.parse(dataWithSync);

const findBestResult = async (data, clusterNumber) => {
  let preScoreCaClustering = Number.MIN_VALUE;
  let preScoreHierarchicalClustering = Number.MIN_VALUE;
  let preScoreKmeansClustering = Number.MIN_VALUE;

  let a = 14;
  while (a--) {
    const levelOneClustersResponse = await fetch(
      `http://localhost:9788/cluster/FindCluster/levelOne?groups=2,2,2&neighbourHood=3&boundary=nullBoundary`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data.operationalData),
      }
    );

    if (levelOneClustersResponse.status === 200) {
      const levelOneClustersData = await levelOneClustersResponse.json();

      const obj = {
        primaryCluster: levelOneClustersData.clusters,
        operationalData: data.operationalData,
      };

      const levelTwoResponse = await fetch(
        `http://localhost:8009/api/v1/cluster/levelTwo?requiredCluster=3&neighbourHood=2&boundary=nullBoundary`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(obj),
        }
      );

      if (levelTwoResponse.status === 200) {
        const levelTwoBody = await levelTwoResponse.json();

        if (levelTwoBody.success === "NO") {
          console.log(levelTwoBody.clusterNumber);
          continue;
        }

        

        const validatioRequestBody = {
          clusterNumber: clusterNumber,
          ca_clusters: levelTwoBody.clusters,
          rawData: data.rawData,
        };

        const validationResponse = await fetch(
          `http://localhost:8000/valid/get`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(validatioRequestBody),
          }
        );

        if (validationResponse.status === 200) {
          const score = await validationResponse.json();

          const ca_sill = score.ca_clustering.silhoutte_score;
          const k_sill = score["k-means"].silhoutte_score;
          const h_sill = score.hierarchical_clustering.silhoutte_score;

          if (ca_sill > preScoreCaClustering) {
            preScoreCaClustering = ca_sill;
          }
          if (k_sill > preScoreKmeansClustering) {
            preScoreKmeansClustering = k_sill;
          }
          if (h_sill > preScoreHierarchicalClustering) {
            preScoreHierarchicalClustering = h_sill;
          }
        }
      }
    }
  }
  return {
    preScoreCaClustering,
    preScoreHierarchicalClustering,
    preScoreKmeansClustering,
  };
};

const maxScore = findBestResult(jsonData, 3);

maxScore.then(
  (res) => {
    console.log(res);
  },
  (err) => {
    console.log(err);
  }
);
