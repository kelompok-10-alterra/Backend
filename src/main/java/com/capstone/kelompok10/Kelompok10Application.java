package com.capstone.kelompok10;

import java.util.ArrayList;

import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.service.interfaces.CategoryService;
import com.capstone.kelompok10.service.interfaces.MemberService;
import com.capstone.kelompok10.service.interfaces.RoleService;
import com.capstone.kelompok10.service.interfaces.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Kelompok10Application {

	public static void main(String[] args) {
		SpringApplication.run(Kelompok10Application.class, args);
	}

	@Bean
	public WebMvcConfigurer getCorsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://sportly.surge.sh", "https://sportly.vercel.app", "https://sportly.rafdev.my.id").allowCredentials(true).allowedMethods("GET", "PUT", "POST", "DELETE");
			}
		};
	}

	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	@Bean
	CommandLineRunner runner(UserService userService, RoleService roleService, CategoryService categoryService, MemberService memberService){
		return args -> {
			roleService.createRole(new RoleEntity(1L, "ROLE_USER", null, null));
			roleService.createRole(new RoleEntity(2L, "ROLE_ADMIN", null, null));
			roleService.createRole(new RoleEntity(3L, "ROLE_SUPER_ADMIN", null, null));

			userService.createUser(new UserEntity(1L, "Manager", "manager", "buatpasswordyangsusah", "manager@email.com", "123456", "Valhalla", null, null, null, null, 0L, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, null, null, new ArrayList<>(), null, null, null));
			userService.createUser(new UserEntity(2L, "Admin", "admin", "yanginijugasusah", "admin@email.com", "123456", "Asgard",  null, null, null, null, 0L, false, new ArrayList<>() ,new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, null, null, new ArrayList<>(), null, null, null));
			userService.createUser(new UserEntity(3L, "User", "user", "yanginigampang", "user@email.com", "123456", "Midgrad", null, null, null, null, 0L, false, new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, null, null, new ArrayList<>(), null, null, null));
			userService.createUser(new UserEntity(4L, "Back End", "backend", "susahbenerinipassword", "backend@email.com", "123456", "Kolong Jembatan", null, null, null, null, 0L, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, null, null, new ArrayList<>(), null, null, null));

			userService.addRoleToUser("manager", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("backend", "ROLE_SUPER_ADMIN");

			categoryService.createCategory(new CategoryEntity(1L, "Online", new ArrayList<>(), null, null));
			categoryService.createCategory(new CategoryEntity(2L, "Offline", new ArrayList<>(), null, null));

			memberService.createMember(new MemberEntity(1L, "Silver", "1 Month", 100000L, new ArrayList<>(), null, null));
			memberService.createMember(new MemberEntity(2L, "Gold", "3 Month", 250000L, new ArrayList<>(), null, null));
			memberService.createMember(new MemberEntity(3L, "Platinum", "6 Month", 500000L, new ArrayList<>(), null, null));
		};
	}

}
