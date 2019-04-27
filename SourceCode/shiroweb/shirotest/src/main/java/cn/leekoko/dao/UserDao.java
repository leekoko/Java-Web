package cn.leekoko.dao;

import cn.leekoko.controller.vo.User;

import java.util.List;

public interface UserDao {

    public User getUserByUserName(String userName);
    public List<String> queryRolesByUserName(String userName);
}
