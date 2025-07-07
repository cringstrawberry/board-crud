package com.sparta.BoardAPI2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 이걸 꼭 추가해야 JPA Auditing이 동작합니다!
public class BoardCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCrudApplication.class, args);
    }

}
