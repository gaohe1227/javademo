package com.demo.shiro.dao;

import java.util.List;
import java.util.Set;

import com.demo.dao.MyBatisRepository;
import com.demo.shiro.entity.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@MyBatisRepository
public interface UserDao {

    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    List<String> findRoles(String username);//获取角色集合

    List<String> findPermissions(String username);
}
