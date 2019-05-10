package com.example.query.benchmark.service;


import com.example.query.benchmark.common.QueryBenchmarkRecursiveAction;
import com.example.query.benchmark.common.QueryBenchmarkResult;
import com.example.query.benchmark.repository.SQLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;


@Service
@Slf4j
public class QueryBenchmarkServiceImpl implements QueryBenchmarkService {

    private final List<SQLRepository> repositories;

    public static ConcurrentHashMap<String, Boolean> threadMap = new ConcurrentHashMap<>();
    public static List<QueryBenchmarkResult> results = new CopyOnWriteArrayList<>();

    @Autowired
    @Testable
    public QueryBenchmarkServiceImpl(List<SQLRepository> repositories) {
        this.repositories = repositories;
    }

    public List<QueryBenchmarkResult> testQuery(String query) {
        resultsCleanUp();
        List<RecursiveAction> tasks = new ArrayList<>();
        for (SQLRepository repository : repositories) {
            tasks.add(new QueryBenchmarkRecursiveAction(query, repository, repository.getClass().getSimpleName()));
        }
        ForkJoinTask.invokeAll(tasks);
        return results;
    }

    private void resultsCleanUp() {
        QueryBenchmarkServiceImpl.results.clear();
    }

}
