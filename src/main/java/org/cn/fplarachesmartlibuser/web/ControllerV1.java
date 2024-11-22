package org.cn.fplarachesmartlibuser.web;


import org.cn.fplarachesmartlibuser.dto.RoleDTO;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")

public class ControllerV1 {
    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    public List<UserDTO> users(){
        return accountService.users();
    }

    @GetMapping("/roles")
    public List<RoleDTO> roles(){
        return accountService.roles();
    }

    @PostMapping("/role")
    public RoleDTO saveRole( @RequestBody RoleDTO role){
        return accountService.saveRole(role);
    }

    @PutMapping("/role/{id}")
    public RoleDTO update(@PathVariable Long id, @RequestBody RoleDTO role){
        role.setId(id);
        return accountService.saveRole(role);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        System.out.println("controller delete user : ");
        accountService.deleteUser(id);
    }

    @DeleteMapping("/role/{id}")
    public void deleteRole(@PathVariable Long id){
        RoleDTO role = accountService.getRole(id);
        accountService.deleteRole(role);
    }

    @GetMapping("/name/{name}")
    public List<UserDTO> getUser(@PathVariable String name){
        return this.accountService.byName(name);
    }


    @PostMapping("/register")
    public UserDTO SignUp(@RequestBody RegistrationForm data){
        String password = data.getPassword();
        String repassword = data.getRepassword();
        if(!password.equals(repassword))
            throw new RuntimeException("You must confirm your password");

        UserDTO user = new UserDTO();
        BeanUtils.copyProperties(data, user);
        accountService.saveUser(user);

        return user;
    }

    @PutMapping("/user/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO user){
        user.setId(id);
        return accountService.saveUser(user);
    }

    @GetMapping("/username/{username}")
    public UserDTO findByUsername(@PathVariable String username){
        return accountService.findByUserName(username);
    }
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id){
        return accountService.byId(id);
    }

}
