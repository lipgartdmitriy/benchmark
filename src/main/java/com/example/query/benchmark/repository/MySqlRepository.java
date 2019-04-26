package com.example.query.benchmark.repository;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlRepository implements SQLRepository {
    @Override
    public String execute(String query) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return query;
    }
}
