from sklearn.cluster import KMeans
from validation_algorithms import algorithms
from scipy.cluster.hierarchy import dendrogram, linkage, fcluster
import pandas as pd
from util_functions import services



def kmeans_clustering_score(data,cluster_number):
    kmeans = KMeans(n_clusters=cluster_number, random_state=42)
    clusters = kmeans.fit_predict(data)
    return {"silhoutte_score":algorithms.calculate_silhoutte(data,clusters),
            "dunn_index_score":algorithms.calculate_dunn_index_for_kmeans(data,clusters,cluster_number)
            }

def hierarchical_cluster_score(data,cluster_number):
    linked = linkage(data, method='ward', metric='euclidean') 
    clusters = fcluster(linked, cluster_number , criterion='maxclust')
    return {"silhoutte_score":algorithms.calculate_silhoutte(data,clusters),
            "dunn_index":algorithms.calculate_dunn_index_for_hierarchical(data,clusters)}

def ca_clustering_score(ca_clusters, total_data_points , data_frame):

    labels = services.map_data_of_ca_cluster(ca_clusters,total_data_points)

    return {
        "silhoutte_score":algorithms.calculate_silhoutte(data_frame,labels),
        "dunn_index_score":algorithms.calculate_dunn_index_for_hierarchical(data_frame,labels)
    }


