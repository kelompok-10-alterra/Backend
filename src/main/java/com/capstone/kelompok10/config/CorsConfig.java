// package com.capstone.kelompok10.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebMvc
// public class CorsConfig {
    
// 	@Bean
// 	public WebMvcConfigurer getCorsConfigurer(){
// 		return new WebMvcConfigurer() {
// 			@Override
// 			public void addCorsMappings(CorsRegistry registry){
// 				registry.addMapping("/**")
//                 .allowedOrigins("**")
// 				.allowedMethods("GET", "POST", "PUT", "DELETE")
// 				.allowedHeaders("*");
// 			}
// 		};
// 	}
// }