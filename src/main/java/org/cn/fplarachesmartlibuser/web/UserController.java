package org.cn.fplarachesmartlibuser.web;

import lombok.AllArgsConstructor;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    //@Autowired
    private AccountService accountService;

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

    @PutMapping("/update/{id}")
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