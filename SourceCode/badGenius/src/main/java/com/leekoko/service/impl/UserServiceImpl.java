package com.leekoko.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leekoko.mapper.UserMapper;
import com.leekoko.pojo.User;
import com.leekoko.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyb
 * @since 2019-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
