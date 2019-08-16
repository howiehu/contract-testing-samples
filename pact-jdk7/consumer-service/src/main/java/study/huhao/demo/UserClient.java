package study.huhao.demo;

import org.springframework.web.client.RestTemplate;

public class UserClient {
    private String serviceUrl;

    public UserClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public User getUserById(int id) {
        return new RestTemplate().getForEntity(serviceUrl + "/users/" + id, User.class).getBody();
    }
}
