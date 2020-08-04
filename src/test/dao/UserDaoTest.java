package test.dao;

import cn.michael.user.dao.UserDao;
import cn.michael.user.domain.User;
import org.junit.Test;


public class UserDaoTest {
    @Test
    public void testFindByUsername(){
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername("wangwu");
        System.out.println(user);
    }

    @Test
    public void testAddUser(){
        UserDao userDao = new UserDao();

        User user = new User();
        user.setUsername("wangwu");
        user.setPassword("123");
        userDao.add(user);
    }

}
