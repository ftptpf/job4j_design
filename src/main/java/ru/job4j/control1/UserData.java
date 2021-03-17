package ru.job4j.control1;

import java.util.Objects;
import java.util.Set;

public class UserData {
    private String name;
    private Set<String> emailSet;

    public UserData(String name, Set<String> emailSet) {
        this.name = name;
        this.emailSet = emailSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getEmailSet() {
        return emailSet;
    }

    public void setEmailSet(Set<String> emailSet) {
        this.emailSet = emailSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserData users = (UserData) o;
        return name.equals(users.name) && emailSet.equals(users.emailSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailSet);
    }
}
