package dev.testament.gateway.security.services;

import dev.testament.gateway.common.configuration.RouteConfiguration;
import dev.testament.gateway.user.models.User;
import dev.testament.gateway.user.services.UserService;
import dev.testament.gateway.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    private RouteConfiguration routeConfiguration;
    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(RouteConfiguration routeConfiguration, UserService userService) {
        Args.notNull("routeConfiguration", routeConfiguration);
        Args.notNull("userService", userService);
        this.routeConfiguration = routeConfiguration;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userService.findUser(routeConfiguration.getUserServiceHost(), email);
        if(user == null) throw new UsernameNotFoundException("User '" + email + "' not found");

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, Collections.EMPTY_LIST);

    }

}
