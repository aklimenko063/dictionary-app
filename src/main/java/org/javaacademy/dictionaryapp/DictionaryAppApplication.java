package org.javaacademy.dictionaryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DictionaryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryAppApplication.class, args);
	}

}
