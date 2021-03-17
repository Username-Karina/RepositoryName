package jm.task.core.jdbc.model;

import javax.persistence.*;


@Entity
@Table (name = "user")
public class User {

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name", unique = true, nullable = false, length = 30)
    private String name;

    @Column (name = "lastname", unique = true, nullable = false, length = 60)
    private String lastName;

    @Column (name = "age",nullable = false, length = 3)
    private Byte age;

    public User() {

    }
    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User " + name + " " + lastName + " " + age;
    }
}
