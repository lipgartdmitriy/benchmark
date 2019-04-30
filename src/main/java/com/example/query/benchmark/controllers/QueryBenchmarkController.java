package com.example.query.benchmark.controllers;


import com.example.query.benchmark.common.QueryBenchmarkResult;
import com.example.query.benchmark.service.QueryBenchmarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/benchmark")
public class QueryBenchmarkController {

    private final QueryBenchmarkService benchmarkService;

    public QueryBenchmarkController(QueryBenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @GetMapping
    public List<QueryBenchmarkResult> getQueryBenchmarkResult(@RequestParam(value = "query") String query) {
        return benchmarkService.testQuery(query);
    }

}
