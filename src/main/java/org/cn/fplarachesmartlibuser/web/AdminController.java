package org.cn.fplarachesmartlibuser.web;


import org.cn.fplarachesmartlibuser.dto.RoleDTO;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
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

}
