package com.akash.modifier.utility;

import java.util.ArrayList;

public class ModifyDataAttributes {

    public ArrayList<ArrayList<String>> deleteArrayIndexes(
            ArrayList<ArrayList<String>> data,
            ArrayList<Integer> indexes) throws IndexOutOfBoundsException{

        if(data==null){
            throw new NullPointerException("Data invalid, Bad request body");
        }

        if(indexes.isEmpty()){
            return data;
        }

        ArrayList<ArrayList<String>> del = new ArrayList<>();
        indexes.forEach(cc->{
            if(cc >= data.size()){
                throw new IndexOutOfBoundsException("The Index is not valid to do the operation");
            }
        });


        for(Integer integer: indexes){
            del.add(data.get(integer));
        }
        data.removeAll(del);
        return data;
    }

    public ArrayList<ArrayList<String>> deleteOneIndex(ArrayList<ArrayList<String>> data,
                                                       Integer index) throws IndexOutOfBoundsException {

        if(data==null)
            throw new NullPointerException("Data invalid, Bad request body");

        if(index>=data.size()){
            throw new IndexOutOfBoundsException("The Index is not valid to do the operation");
        }
        ArrayList<String> arrayList = data.get(index);
        data.remove(arrayList);
        return data;
    }

    public ArrayList<ArrayList<String>> dataAddOnHandle(ArrayList<ArrayList<String>> data, ArrayList<String> addOnData){

        if(addOnData==null && data==null){
            throw new NullPointerException("Data invalid, Bad request body");
        }

        if(data==null){
            ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
            arrayList.add(addOnData);
            return arrayList;
        }
        if(addOnData==null){
            return data;
        }

        data.add(addOnData);

     return data;
    }
    public ArrayList<ArrayList<String>> multipleDataAddOnHandle(ArrayList<ArrayList<String>> data, ArrayList<ArrayList<String>> addOnData){

        if(addOnData==null && data==null){
            throw new NullPointerException("Data invalid, Bad request body");
        }

        if(data==null){
            return new ArrayList<>(addOnData);
        }
        if(addOnData==null){
            return data;
        }
        data.addAll(addOnData);
        return data;
    }
}
