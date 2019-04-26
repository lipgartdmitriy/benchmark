package com.example.query.benchmark.service;


import com.example.query.benchmark.common.QueryBenchmarkRecursiveAction;
import com.example.query.benchmark.common.QueryBenchmarkResult;
import com.example.query.benchmark.repository.MySqlRepository;
import com.example.query.benchmark.repository.OracleRepository;
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
public class QueryBenchmarkService {

    private final MySqlRepository mySqlRepository;
    private final OracleRepository oracleRepository;

    public static ConcurrentHashMap<String, Boolean> threadMap = new ConcurrentHashMap<>();
    public static List<QueryBenchmarkResult> results = new CopyOnWriteArrayList<>();

    @Autowired
    public QueryBenchmarkService(MySqlRepository mySqlRepository, OracleRepository oracleRepository) {
        this.mySqlRepository = mySqlRepository;
        this.oracleRepository = oracleRepository;
    }

    public List<QueryBenchmarkResult> testQuery(String query) {

        List<RecursiveAction> tasks = new ArrayList<>();
        tasks.add(new QueryBenchmarkRecursiveAction(query, mySqlRepository, "MySQL DB"));
        tasks.add(new QueryBenchmarkRecursiveAction(query, oracleRepository, "Oracle DB"));

        ForkJoinTask.invokeAll(tasks);

        return results;
    }

}
