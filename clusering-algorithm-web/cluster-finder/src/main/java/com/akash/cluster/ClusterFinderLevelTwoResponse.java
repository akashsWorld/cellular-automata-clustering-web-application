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
    ArrayList<ArrayList<Integer>> clusters;
}
