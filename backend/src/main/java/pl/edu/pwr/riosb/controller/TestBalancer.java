package pl.edu.pwr.riosb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryCarRepository;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/test")
public class TestBalancer {

    @GetMapping("/load-balancer/primary")
    public ResponseEntity testPrimaryLoadBalancer(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8080/CAR_RENTAL");
        dataSource.setUsername("RIOSB");
        dataSource.setPassword("TEST");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        SqlRowSet sqlRowSet = template.queryForRowSet("SELECT * FROM CARS;");

        return ResponseEntity.ok(null);
    }

    @GetMapping("/load-balancer/secondary")
    public ResponseEntity testSecondaryLoadBalancer(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:443/CAR_RENTAL");
        dataSource.setUsername("RIOSB");
        dataSource.setPassword("TEST");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        SqlRowSet sqlRowSet = template.queryForRowSet("SELECT * FROM CARS;");

        return ResponseEntity.ok(null);
    }
}
