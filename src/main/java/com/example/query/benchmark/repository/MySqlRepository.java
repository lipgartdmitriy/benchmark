package com.example.query.benchmark.repository;
import com.example.query.benchmark.service.Testable;
import org.springframework.stereotype.Repository;

@Repository
@Testable
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
