package com.TechMojo.Top10TwitterHashtags;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.TechMojo"})
@EnableScheduling
@Slf4j
public class Top10TrendingTwitterHashTags {

	public static void main(String[] args) {
		SpringApplication.run(Top10TrendingTwitterHashTags.class, args);
	}

}
