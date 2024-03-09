from django.views import View
import json
from django.http import JsonResponse
import pandas as pd
from util_functions import services
from cluster_algorithms import clustering_algorithm


class ValidView(View):
    def post(self,request):
        if(request.method =="POST"):
            try:

                request_body = json.loads(request.body.decode('utf-8'))

                original_data = request_body['rawData']
                cluster_number = request_body["clusterNumber"]
                ca_clusters_data = request_body["ca_clusters"]

                converted_data = services.convert_data(original_data)
                
                data_frame = pd.DataFrame(converted_data)

                total_data_points = len(converted_data)


                k_means_cluster_score = clustering_algorithm.kmeans_clustering_score(data_frame,cluster_number)
                hierarchical_cluster_score = clustering_algorithm.hierarchical_cluster_score(data_frame,cluster_number)
                ca_clustering_cluster_score = clustering_algorithm.ca_clustering_score(ca_clusters_data,total_data_points,data_frame)


                return JsonResponse({
                    'k-means':k_means_cluster_score,
                    'hierarchical_clustering':hierarchical_cluster_score,
                    "ca_clustering":ca_clustering_cluster_score
                })
            except json.JSONDecodeError:
                return JsonResponse({'error': 'Invalid JSON format in request body'}, status=400)
        else:
            return JsonResponse({'error': 'Invalid request method'}, status=405)
        




        
