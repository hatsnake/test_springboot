package com.hatsnake.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 어노테이션들을 모두 활성화
@EnableJpaAuditing
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// 특히 @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에
// 이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
