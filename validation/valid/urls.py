from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    path("get", views.ValidView.as_view(), name="valid")
]
