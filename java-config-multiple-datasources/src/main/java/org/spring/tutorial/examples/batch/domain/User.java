package org.spring.tutorial.examples.batch.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    private long id;
    private String name;
    private String email;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(long id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}