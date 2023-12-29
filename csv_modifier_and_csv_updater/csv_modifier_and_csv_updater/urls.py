from django.urls import path,include

urlpatterns = [
    path('readCsv', include('csv_reader.urls'))
]
