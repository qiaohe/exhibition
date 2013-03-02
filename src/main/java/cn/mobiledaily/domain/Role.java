package cn.mobiledaily.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;

public enum Role implements GrantedAuthority {
    NONE(""),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ;

    private static Map<String, Role> roleMap = new HashMap<>();

    static {
        for (Role role : Role.values()) {
            roleMap.put(role.roleName, role);
        }
    }

    public static Role fromString(String val) {
        Role role = roleMap.get(val.toUpperCase());
        if (role == null) {
            role = NONE;
        }
        return role;
    }

    private String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
