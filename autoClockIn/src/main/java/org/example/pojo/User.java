package org.example.pojo;

public class User {
    private String name;
    private String id;

    public User() {
    }

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "user{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
