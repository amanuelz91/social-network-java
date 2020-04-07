// Largely based off https://medium.com/@sheikarbaz5/spring-boot-with-tdd-test-driven-development-part-i-be1b90da51e
package com.amanuel.socialnetwork;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner setup(PostRepository postRepository) {
//		return (args) -> {
//			postRepository.save(new Post("Add a new test case", new Date()));
//			postRepository.save(new Post("Make it fail", new Date()));
//			postRepository.save(new Post("Do changes to the code", new Date()));
//			postRepository.save(new Post("Pass the test", new Date()));
//		};
//	}

}
