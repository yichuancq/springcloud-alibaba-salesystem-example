package com.example.oauth.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yichuan
 */
@Getter
@Setter
public class BaseUser extends User {
    private static final long serialVersionUID = BaseUser.class.hashCode();
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 用户id
     */
    private Long userId;
    private List<Long> roleIds;
    private Map<String, Object> params;

    public BaseUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public BaseUser(String username, String password, boolean enabled, boolean accountNonExpired,
                    boolean credentialsNonExpired, boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public BaseUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                    String clientId, Long userId, List<Long> roleIds, Map<String, Object> params) {
        super(username, password, authorities);
        this.clientId = clientId;
        this.userId = userId;
        this.roleIds = roleIds;
        this.params = params;
    }


}
