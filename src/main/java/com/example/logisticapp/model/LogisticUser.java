package com.example.logisticapp.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.Set;

@Entity
public class LogisticUser {
    @Id
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public LogisticUser() {

    }
    public LogisticUser(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogisticUser logisticUser = (LogisticUser) o;
        return Objects.equals(username, logisticUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
