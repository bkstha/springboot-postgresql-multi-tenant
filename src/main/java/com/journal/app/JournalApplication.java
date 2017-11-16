package com.journal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JournalApplication {

    //	@Autowired
//	@Qualifier("datasource")
//	private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

//	@Override
//	public void run(String... str) throws SQLException{
//		System.out.println("data source");
//		System.out.println(dataSource.getConnection().getSchema());
//	}
}
