package com.akash.modifier.utility;

import java.util.ArrayList;

public class ModifyDataAttributes {

    public ArrayList<ArrayList<String>> deleteArrayIndexes(
            ArrayList<ArrayList<String>> data,
            ArrayList<Integer> indexes) throws IndexOutOfBoundsException{

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
        if(index>=data.size()){
            throw new IndexOutOfBoundsException("The Index is not valid to do the operation");
        }
        ArrayList<String> arrayList = data.get(index);
        data.remove(arrayList);
        return data;
    }
}
