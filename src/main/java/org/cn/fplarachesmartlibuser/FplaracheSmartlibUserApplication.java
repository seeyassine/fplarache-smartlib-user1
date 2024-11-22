package org.cn.fplarachesmartlibuser;

import org.cn.fplarachesmartlibuser.dao.UserRepository;
import org.cn.fplarachesmartlibuser.dto.RoleDTO;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FplaracheSmartlibUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FplaracheSmartlibUserApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService, UserRepository userRepository){
        return args -> {
            accountService.saveRole(new RoleDTO(null,"USER"));
            accountService.saveRole(new RoleDTO(null,"ADMIN"));
            accountService.saveUser(new UserDTO(1L,"nouhaila","nouha1234","NOUHAILA CHKHONTY","nohaila.chkhonty@gmail.com","Hay salam groupe O Rue 12  numero 33, ksar el kebir","0625614992", null));
            accountService.saveUser(new UserDTO(2L,"admin","12345678","ADMIN","admin@gmail.com","Hay salam groupe O Rue 12  numero 33, ksar el kebir","0625614992", null));
            accountService.addRoleToUser("admin", "ADMIN");
            //System.out.println(userRepository.findAll());
        };
    }

}
