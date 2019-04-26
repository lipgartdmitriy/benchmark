package com.example.query.benchmark.repository;
import org.springframework.stereotype.Repository;

@Repository
public class OracleRepository implements SQLRepository {
    @Override
    public String execute(String query) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return query;
    }
}
