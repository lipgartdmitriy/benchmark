package com.example.query.benchmark.service;

import com.example.query.benchmark.common.QueryBenchmarkResult;

import java.util.List;

public interface QueryBenchmarkService {
    List<QueryBenchmarkResult> testQuery(String query);
}
