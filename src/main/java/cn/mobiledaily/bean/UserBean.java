package cn.mobiledaily.bean;

import cn.mobiledaily.model.User;
import cn.mobiledaily.service.IUserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class UserBean {
    @ManagedProperty("#{userService}")
    private IUserService userService;

    private User newUser = new User();

    public void saveUser() {
        try {
            userService.persist(newUser);
            newUser = new User();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
