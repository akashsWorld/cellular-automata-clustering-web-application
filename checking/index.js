const fs = require('fs')

const dataWithSync = fs.readFileSync("./checking/dummy-db.json", "utf-8");

const jsonData = JSON.parse(dataWithSync);

const findBestResult = async ( data ,clusterNumber)=>{

    
    let preScore = Number.MIN_VALUE
    let a= 1000;
    while(a--){
        const levelOneClustersResponse  = await fetch(`localhost:9788/cluster/FindCluster/levelOne?groups=2,2,2&neighbourHood=3&boundary=nullBoundary`,{
            method:'GET',
            body:JSON.stringify(data.operationalData)
        })
        if(levelOneClustersResponse.status === 200){
            const levelOneClusters = await levelOneClustersResponse.body.json().clusters

            const obj = {
                primaryCluster: levelOneClusters,
                operationalData: data.operationalData
            }

            const levelTwoResponse = await fetch(`localhost:9788/cluster/FindCluster/levelTwo?requiredCluster=3&neighbourHood=3&boundary=nullBoundary`,{
                method:'GET',
                body:JSON.stringify(obj)
            })

            if(levelTwoResponse.status === 200){
                const levelTwoBody = await levelTwoResponse.body.json().clusters

                const validatioRequestBody = {
                    clusterNumber:clusterNumber,
                    ca_clusters:levelTwoBody,
                    rawData:data.rawData
                }

                const validationResponse = await fetch(`localhost:8000/valid/get`,{
                    method:'GET',
                    body:JSON.stringify(validatioRequestBody)
                })

                if(validationResponse.status === 200){
                    const score = await validationResponse.body.json().ca_clustering;
                    
                    if(score> preScore){
                        preScore = score;
                    }
                }

            }

        }
    }

}

findBestResult(jsonData);


