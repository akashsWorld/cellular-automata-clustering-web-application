package com.akash.cluster;


import lombok.*;

import java.util.ArrayList;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClusterFinderLevelTwoResponse {

    private Integer resets;
    private Integer levels;
    private Integer clusterNumber;
    private String success;
    ArrayList<ArrayList<Integer>> clusters;
}
