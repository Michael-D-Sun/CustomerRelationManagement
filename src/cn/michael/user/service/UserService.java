package cn.michael.user.service;

import cn.michael.user.dao.UserDao;
import cn.michael.user.domain.User;

public class UserService {
    private  UserDao userDao = new UserDao();

    public void register(User user) throws UserException{
        User _user = userDao.findByUsername(user.getUsername());

        if(_user != null) {
            throw new UserException("Username " + user.getUsername() + ", has been registered");
        }
        userDao.add(user);
    }

    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());

        if(user == null){
            throw new UserException("Username doesn't exist");
        }

        if(!form.getPassword().equals(user.getPassword())){
            System.out.println("password is not right");
            throw new UserException("Password is not right");
        }

        return user;
    }

}
