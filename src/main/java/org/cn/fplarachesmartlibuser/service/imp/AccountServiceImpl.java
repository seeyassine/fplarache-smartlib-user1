package org.cn.fplarachesmartlibuser.service.imp;

import lombok.AllArgsConstructor;

import org.cn.fplarachesmartlibuser.dao.RoleRepository;
import org.cn.fplarachesmartlibuser.dao.UserRepository;
import org.cn.fplarachesmartlibuser.dto.RoleDTO;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.entity.AppRole;
import org.cn.fplarachesmartlibuser.entity.AppUser;
import org.cn.fplarachesmartlibuser.mapper.RoleMapper;
import org.cn.fplarachesmartlibuser.mapper.UserMapper;
import org.cn.fplarachesmartlibuser.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private RoleMapper roleMapper;

    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        System.out.println("-- service : save user  ---");
        AppUser user = userRepository.findByUsername(userDTO.getUsername());
        if(user != null) throw new RuntimeException("this user already exits.");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO = userMapper.fromUser(userRepository.save(userMapper.fromUserDTO(userDTO)));
        this.addRoleToUser(userDTO.getUsername(), "USER");
        System.out.println("----- user : " + userDTO.toString());
        return userDTO;
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        System.out.println("-- service : save role  ---");
        return roleMapper.fromRole(roleRepository.save(roleMapper.fromRoleDTO(roleDTO)));
    }

    @Override
    public UserDTO findByUserName(String username) {
        System.out.println("-- service : find user by username ---");
        return userMapper.fromUser(userRepository.findByUsername(username));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        System.out.println("-- service : add role to user  ---");
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRole(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<UserDTO> users() {
        System.out.println("-- service : find users ---");
        return userRepository.findAll().stream().map(u -> userMapper.fromUser(u)).collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> roles() {
        System.out.println("-- service : find roles ---");
        return roleRepository.findAll().stream().map(r -> roleMapper.fromRole(r)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("-- service : delete user ---");
        userRepository.deleteById(id);
    }

    @Override
    public void deleteRole(RoleDTO role) {
        System.out.println("-- service : delete role ---");
        roleRepository.delete(roleMapper.fromRoleDTO(role));
    }

    @Override
    public UserDTO getUser(Long id) {
        System.out.println("-- service : find user by id ---");
        return userMapper.fromUser(userRepository.findById(id).get());
    }

    @Override
    public RoleDTO getRole(Long id) {
        System.out.println("-- service : find user by role ---");
        return roleMapper.fromRole(roleRepository.findById(id).get());
    }

    @Override
    public List<UserDTO> byName(String name) {
        System.out.println("-- service : find user by name ---");
        return userRepository.findByNameContainingIgnoreCase(name).stream().map(u -> userMapper.fromUser(u)).collect(Collectors.toList());
    }

    @Override
    public UserDTO byId(Long id) {
        System.out.println("-- service : find user by id ---");
        return userMapper.fromUser(userRepository.findById(id).get());
    }
}
