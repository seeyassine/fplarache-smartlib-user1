package org.cn.fplarachesmartlibuser.dao;

import org.cn.fplarachesmartlibuser.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Long> {
    public AppRole findByRole(String role);
}
