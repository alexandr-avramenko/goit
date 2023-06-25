package org.example;

import org.example.models.Address;
import org.example.models.Geo;
import org.example.models.User;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequestTest http = new HttpRequestTest();
        Optional<String> users = http.getUsers();
//        System.out.println(users.orElse("Not found"));
        Geo geo = new Geo(
                "23523",
                "124123"
        );
        Address adress = new Address(
                "nature",
                "13A",
                "Kyiv",
                "10031",
                geo
        );
        User ivan = new User(
                "Ivan",
                "Gladiator",
                "gladiator2@gmail.com",
                adress
                );
//        Update user
        Optional<String> user = http.updateUser(ivan, 10);
//        Create user
        Optional<String> user2 = http.createUser(ivan);
//        Delete user
//        System.out.println(http.deleteUser(1));
//        Get users
//        System.out.println(http.getUsers());
//        System.out.println(user.orElse("Not created"));
//        Get user by id
//        System.out.println(http.getUser(1));
//        Get user by userName
//        System.out.println(http.getUser("ss"));
//        http.getComments(1);
        http.getToDoTask(1);
    }
}