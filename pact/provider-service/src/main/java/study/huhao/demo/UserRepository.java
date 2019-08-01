package study.huhao.demo;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final List<User> users = Arrays.asList(
            User.builder().id(1).name("Alex").age(10).build(),
            User.builder().id(2).name("Bill").age(20).build(),
            User.builder().id(3).name("Charlie").age(30).build(),
            User.builder().id(4).name("Davis").age(40).build(),
            User.builder().id(5).name("Emma").age(50).build()
    );

    public Optional<User> getById(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny();
    }
}
