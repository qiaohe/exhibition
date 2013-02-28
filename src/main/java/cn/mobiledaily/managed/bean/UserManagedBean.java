package cn.mobiledaily.managed.bean;

import cn.mobiledaily.model.User;
import cn.mobiledaily.service.IUserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userMB")
public class UserManagedBean implements Serializable {
    @ManagedProperty("#{userService}")
    private IUserService userService;
    private int id;
    private String name;

    public String addUser() {
        User user = new User();
        user.setName(name);
        try {
            userService.persist(user);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        List<User> users = userService.getUsers();
        return "index";
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
