package cn.mobiledaily.web.mangedbean;

import cn.mobiledaily.domain.User;
import cn.mobiledaily.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class UserBean {
    @ManagedProperty("#{userServiceImpl}")
    private UserService userService;

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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
