from django.http import JsonResponse
import pandas as pd
from django.views import View


class UploadData(View):
    def post(self,request):
        a = request.FILES['dataset']
        df = pd.read_csv(a)
        data_dictionay  = df.to_dict()

        attribute_list = list(data_dictionay.keys())

        data_dictionary_list = list(data_dictionay.values())

        data_list=[]

        for data in data_dictionary_list:
            data_list.append(list(data.values()))

        response_data=[]
        for i in range(len(data_list)):
            temp_list =[]
            for j in range(len(attribute_list)):
                temp_list.append(data_list[j][i])
            
            response_data.append(temp_list)
        return JsonResponse({
            "attribute_list":attribute_list,
            "data":response_data
        })
