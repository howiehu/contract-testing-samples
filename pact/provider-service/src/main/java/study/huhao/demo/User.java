package study.huhao.demo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private int id;
    private String name;
    private int age;
}
