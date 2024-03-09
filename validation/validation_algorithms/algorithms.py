from sklearn.metrics import silhouette_score
from sklearn.metrics import pairwise_distances
import numpy as np


def calculate_silhoutte(data,label):
    score = silhouette_score(data, label)
    return score

def calculate_dunn_index_for_kmeans(data,labels,cluster_number):
    intra_cluster_distances = []
    inter_cluster_distances = []

    for cluster_id in range(cluster_number):
        cluster_points = data[labels == cluster_id]
    
    # Intra-cluster distances (distance between points within the same cluster)
        intra_distances = pairwise_distances(cluster_points, metric='euclidean')
        intra_cluster_distances.extend(intra_distances[np.triu_indices(len(intra_distances), k=1)])

    # Inter-cluster distances (distance between different clusters)
        other_clusters = data[labels != cluster_id]
        inter_distances = pairwise_distances(cluster_points, other_clusters, metric='euclidean')
        inter_cluster_distances.extend(inter_distances.flatten())

    # Calculate Dunn Index
    min_inter_cluster_distance = min(inter_cluster_distances)
    max_intra_cluster_distance = max(intra_cluster_distances)

    dunn_index = min_inter_cluster_distance / max_intra_cluster_distance
    return dunn_index



def calculate_dunn_index_for_hierarchical(data,clusters):
    intra_cluster_distances = []
    inter_cluster_distances = []

    for cluster_id in np.unique(clusters):
        cluster_points = data[clusters == cluster_id]
    
    # Intra-cluster distances (distance between points within the same cluster)
        intra_distances = pairwise_distances(cluster_points, metric='euclidean')
        np.fill_diagonal(intra_distances, np.inf)  # Exclude self-distances
        intra_cluster_distances.extend(intra_distances.flatten())

    # Inter-cluster distances (distance between different clusters)
        other_clusters = data[clusters != cluster_id]
        inter_distances = pairwise_distances(cluster_points, other_clusters, metric='euclidean')
        inter_cluster_distances.extend(inter_distances.flatten())

    # Calculate Dunn Index
    min_inter_cluster_distance = min(inter_cluster_distances)
    max_intra_cluster_distance = max(intra_cluster_distances)

    dunn_index = min_inter_cluster_distance / max_intra_cluster_distance
    return dunn_index


