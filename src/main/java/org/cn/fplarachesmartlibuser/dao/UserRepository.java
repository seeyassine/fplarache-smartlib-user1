package org.cn.fplarachesmartlibuser.dao;

import org.cn.fplarachesmartlibuser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
    public List<AppUser> findByNameContainingIgnoreCase(String name);
}
