package com.example.query.benchmark.repository;
import com.example.query.benchmark.service.Testable;
import org.springframework.stereotype.Repository;

@Repository
@Testable
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
