package dev.testament.gateway.user.services;

import dev.testament.gateway.user.models.User;
import dev.testament.gateway.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        Args.notNull("restTemplate", restTemplate);
        this.restTemplate = restTemplate;
    }

    @Override
    public User findUser(String host, String email) {
        if(email.equals("johndoe01@test.testament.dev")) {
            User user = new User();
            user.setEmail("johndoe01@test.testament.dev");
            user.setPassword("$2a$10$xwI/06f.GkP28ChJLzZlh.XzCfcYnDF59X7yAPWS7rsL0xvWZPyQi");
            return user;
        }

        return null;
        //return restTemplate.getForObject(host + "/users/" + username, User.class);
    }

}
