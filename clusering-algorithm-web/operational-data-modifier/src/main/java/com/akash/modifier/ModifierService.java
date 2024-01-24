package com.akash.modifier;


import com.akash.modifier.utility.ModifyDataAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ModifierService {

    private final ModifyDataAttributes deleteArrayIndexes;
    public ArrayList<ArrayList<String>> deleteIndexes(ArrayList<ArrayList<String>> data,
                                                      ArrayList<Integer> indexes) throws IndexOutOfBoundsException{
        return deleteArrayIndexes.deleteArrayIndexes(data,indexes);
    }

    public ArrayList<ArrayList<String>> deleteOneIndex(ArrayList<ArrayList<String>> data, Integer index){
        return deleteArrayIndexes.deleteOneIndex(data,index);
    }
}
