package io.github.italoalcantaraa.treino.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private String id;
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {}

    @Override
    public String toString() {
        return String.format("{ id = %s, name = %s, age = %s }", id, name, age);
    }
}
