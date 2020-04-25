package com.cenfotec.UUIDAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UuidapiApplication {
	
    @Value("${TARGET:World}")
    String target;

    @RestController
    class HelloworldController {
            @GetMapping("/")
            String hello() {
                    return "Hello UUID" + target + "!";
            }
    }

	public static void main(String[] args) {
		SpringApplication.run(UuidapiApplication.class, args);
	}

}
