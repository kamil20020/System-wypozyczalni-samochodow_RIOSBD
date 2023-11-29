package pl.edu.pwr.riosb;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    //private final TestRepository testRepository;

    @GetMapping
    public ResponseEntity testGet(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8080/CAR_RENTAL");
        dataSource.setUsername("RIOSB");
        dataSource.setPassword("TEST");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        SqlRowSet sqlRowSet = template.queryForRowSet("SELECT * FROM TEST;");

        return ResponseEntity.ok(null);
    }
}
