package com.akash.cluster.controller;


import com.akash.cluster.service.ClusterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/cluster")
@RequiredArgsConstructor
public class ClusterController {
    private final ClusterService clusterService;


}
