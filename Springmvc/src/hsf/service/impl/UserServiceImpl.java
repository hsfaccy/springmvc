package hsf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hsf.dao.UserMapper;
import hsf.model.User;
import hsf.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	@Override
	public User getUserById(int userid) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userid);
	}
	@Override
	public List<User> getUserList(int schoolid) {
		return this.userMapper.getUserList(schoolid);
	}


}
