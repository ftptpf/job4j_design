package ru.job4j.control1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users {
    private String name;
    private List<String> emailList;

    public Users(String name, List<String> emailArrayList) {
        this.name = name;
        this.emailList = emailArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return name.equals(users.name) && emailList.equals(users.emailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailList);
    }
}
