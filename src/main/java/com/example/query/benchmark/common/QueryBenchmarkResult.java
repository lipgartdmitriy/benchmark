package com.example.query.benchmark.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QueryBenchmarkResult {
    private Long time;
    private String dbName;
}
