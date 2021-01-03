package com.example.spring.rest.dao;

import com.example.spring.rest.dto.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static int usersCounter = 3 ;
    private static List<User> users = new ArrayList<User>(){{
        add(new User(1, "Shirish", LocalDate.of(1986,8,4)));
        add(new User(2, "Suresh", LocalDate.of(1986,5,4)));
        add(new User(3, "Vijay", LocalDate.of(1987,5,8)));
    }};

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        System.out.println("usersCounter "+usersCounter);
        if(null == user.getId()){
            user.setId(++usersCounter);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        return users.stream().filter(user -> id == user.getId()).findFirst().orElse(null);
    }

}
