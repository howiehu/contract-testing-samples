package study.huhao.demo;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private static final List<User> users = Arrays.asList(
            User.builder().id(1).name("Alex").age(10).build(),
            User.builder().id(2).name("Bill").age(20).build(),
            User.builder().id(3).name("Charlie").age(30).build(),
            User.builder().id(4).name("Davis").age(40).build(),
            User.builder().id(5).name("Emma").age(50).build()
    );

    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
