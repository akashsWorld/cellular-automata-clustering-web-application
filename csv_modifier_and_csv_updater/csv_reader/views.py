from django.http import JsonResponse,HttpResponse
import pandas as pd
from django.views import View




class ShowData(View):
    def post(self,request):

        if(request.method=='POST'):

            try:
                a=request.FILES['dataset']
            
            except Exception :

                return JsonResponse(
            {'message':'ServerError',
             'explanation':'File Not Found'
            }, status=501
            )
             
            

            if( a== None and a.content_type != 'text/csv'):
                return JsonResponse(
            {'message':'FileNotSupported',
             'explanation':'File type not supported'
            }, status=501
            )

            df = pd.read_csv(a)
            data_dictionay  = df.to_dict()

            attribute_list = list(data_dictionay.keys())

            data_dictionary_list = list(data_dictionay.values())

            data_list=[]

            for data in data_dictionary_list:
                data_list.append(list(data.values()))

            response_data=[]

            for i in range(len(data_list[0])):
                temp_list =[]
                for j in range(len(attribute_list)):
                    temp_list.append(str(data_list[j][i]))
                response_data.append(temp_list)

            operational_data =[]

            for i in range(len(attribute_list)):
                temp_list =[]
                for j in range(len(data_list[0])):
                    temp_list.append(str(data_list[i][j]))

                operational_data.append(temp_list)

            return JsonResponse({
                "attribute_number":len(attribute_list),
                'data_points':len(response_data),
                "attribute_list":attribute_list,
                "data":response_data,
                "operational_data":operational_data
            })
        
        return JsonResponse(
            {'message':'Method Type error',
             'explanation':'The request method not supported'
            }, status=501
        )
