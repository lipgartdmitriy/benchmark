package com.example.query.benchmark.common;


import com.example.query.benchmark.repository.SQLRepository;
import com.example.query.benchmark.service.QueryBenchmarkService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;

@Slf4j
public class QueryBenchmarkRecursiveAction extends RecursiveAction {

    private final com.example.query.benchmark.repository.SQLRepository SQLRepository;
    private final String query;
    private final String dbName;

    public QueryBenchmarkRecursiveAction(String query, SQLRepository sqlRepository, String dbName) {
        SQLRepository = sqlRepository;
        this.query = query;
        this.dbName = dbName;
    }

    @Override
    protected void compute() {
        while (QueryBenchmarkService.threadMap.get(dbName) != null) {
            try {
                this.wait(100);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
        QueryBenchmarkService.threadMap.put(dbName, true);
        String queryResult = null;
        long startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            queryResult = SQLRepository.execute(query);
        }
        long endTime = System.nanoTime();
        long resultTime = (endTime - startTime) / 100;
        QueryBenchmarkService.threadMap.put(dbName, false);
        System.out.println(queryResult);
        QueryBenchmarkService.results.add(new QueryBenchmarkResult(resultTime, dbName));
    }
}
