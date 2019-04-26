package com.example.query.benchmark.service;

import com.example.query.benchmark.BenchmarkApplication;
import com.example.query.benchmark.common.QueryBenchmarkResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = BenchmarkApplication.class)
@RunWith(SpringRunner.class)
public class QueryBenchmarkServiceTest {

    @Autowired
    private QueryBenchmarkService queryBenchmarkService;

    @Test
    public void testQuery() {

        String query = "query";
        List<QueryBenchmarkResult> queryBenchmarkResults = queryBenchmarkService.testQuery(query);
        for (QueryBenchmarkResult queryBenchmarkResult : queryBenchmarkResults) {
            System.out.println(queryBenchmarkResult.getDbName());
            System.out.println(queryBenchmarkResult.getTime());
        }
    }
}