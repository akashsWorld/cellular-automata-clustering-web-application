package com.akash.cellular_automata;

import com.akash.caclustering.boundaries.Boundaries;
import com.akash.caclustering.utility.Boundary;
import com.akash.client.exception.BoundaryNotFoundException;

public class CAHelper {

    public Boundary getBoundary(String boundaryName){

        return switch (boundaryName){
            case "nullBoundary"-> Boundaries::nullBoundary;
            case "notNullBoundary"->Boundaries::notNullBoundary;
            case "intermediateBoundary"->Boundaries::intermediateBoundary;
            case "reflexiveBoundary"-> Boundaries::reflexiveBoundary;
            case "periodicBoundary"-> Boundaries::periodicBoundary;
            case "adiabaticBoundary"->Boundaries::adiabaticBoundary;
            default -> throw new BoundaryNotFoundException("Boundary name not valid.");
        };
    }
}
