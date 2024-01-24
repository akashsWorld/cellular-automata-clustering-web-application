package com.akash.modifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataAddRequest<T> {
    private ArrayList<ArrayList<String>> operationalData;
    private T addOnData;
}
