package hsf.service;

import java.util.List;

import hsf.model.User;

public interface UserService {
	
	public User getUserById(int userid);
	
	public List<User> getUserList(int schoolid);

}
