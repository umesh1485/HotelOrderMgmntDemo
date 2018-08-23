package com.example.HotelOrderMgmntDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.HotelOrderMgmntDemo.event.UserEventsProcessor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {

	// @Bean
	// public Docket productApi() {
	// return new Docket(DocumentationType.SWAGGER_2)
	// .select().apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
	// .paths(RegexpURLValidator. regex( "/order.*"))
	// .build();
	// }
	@Bean
	public UserEventsProcessor eventProcessor() {
		return new UserEventsProcessor();
	}
}
