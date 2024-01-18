from django.urls import path
from . import views


urlpatterns = [
    path('upload',views.ShowData.as_view())
]