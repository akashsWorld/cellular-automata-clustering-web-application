from django.urls import path,include


urlpatterns = [
    path('valid/', include('valid.urls')),
]

