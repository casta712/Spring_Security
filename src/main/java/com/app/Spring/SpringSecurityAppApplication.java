package com.app.Spring;

import com.app.Spring.persistence.entity.PermissionEntity;
import com.app.Spring.persistence.entity.RoleEntity;
import com.app.Spring.persistence.entity.RoleEnum;
import com.app.Spring.persistence.entity.UserEntity;
import com.app.Spring.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {

			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE")
					.build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ")
					.build();

			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE")
					.build();

			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE")
					.build();

			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR")
					.build();


			// Create Roles

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();


			// create users

			UserEntity userFren = UserEntity.builder()
					.username("fren")
					.password("$2a$10$SQ2qX6QrvRtpXjiMi9H3WO3sFEQ1x16T8fSW3ndJf3hlfre3HWeVO")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userLaura = UserEntity.builder()
					.username("laura")
					.password("$2a$10$SQ2qX6QrvRtpXjiMi9H3WO3sFEQ1x16T8fSW3ndJf3hlfre3HWeVO")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userNury = UserEntity.builder()
					.username("nury")
					.password("$2a$10$SQ2qX6QrvRtpXjiMi9H3WO3sFEQ1x16T8fSW3ndJf3hlfre3HWeVO")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userNikol = UserEntity.builder()
					.username("nikol")
					.password("$2a$10$SQ2qX6QrvRtpXjiMi9H3WO3sFEQ1x16T8fSW3ndJf3hlfre3HWeVO")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userFren, userLaura, userNury, userNikol));
		};
	}

}
