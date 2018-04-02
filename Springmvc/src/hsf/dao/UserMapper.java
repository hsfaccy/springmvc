package hsf.dao;

import java.util.List;

import hsf.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);
    
    List<User> getUserList(Integer schoolid);

}