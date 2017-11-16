package com.journal.app;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class JournalApplication {

    //	@Autowired
//	@Qualifier("datasource")
//	private DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(JournalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

//	@Override
//	public void run(String... str) throws SQLException{
//		System.out.println("data source");
//		System.out.println(dataSource.getConnection().getSchema());
//	}
}
