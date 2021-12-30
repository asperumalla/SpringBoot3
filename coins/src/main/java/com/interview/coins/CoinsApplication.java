package com.interview.coins;

import com.interview.coins.test.Parent;
import com.interview.coins.test.ParentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;

@SpringBootApplication
public class CoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
	}

}
