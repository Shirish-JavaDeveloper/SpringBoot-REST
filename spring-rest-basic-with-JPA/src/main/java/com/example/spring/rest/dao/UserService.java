package com.example.spring.rest.dao;

import com.example.spring.rest.dto.Post;
import com.example.spring.rest.dto.User;
import com.example.spring.rest.exceptions.UserAlreadyExistsException;
import com.example.spring.rest.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static int usersCounter = 3 ;
    private static int postCounter = 0 ;
    private static List<User> users = new ArrayList<User>(){{
        add(new User(1, "Shirish", LocalDate.of(1986,8,4)));
        add(new User(2, "Suresh", LocalDate.of(1986,5,4)));
        add(new User(3, "Vijay", LocalDate.of(1987,5,8)));
    }};
    private static List<Post> posts = new ArrayList<>();

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(null == user.getId()){
            user.setId(++usersCounter);
        }
        boolean userExists =  users.stream()
                .anyMatch(userObject -> userObject.getId().equals(user.getId()));

       if(userExists){
           throw new UserAlreadyExistsException(String.format("user with id - [%d] already exists ",user.getId()));
        }

        users.add(user);
        return user;
    }

    public User findOne(int id){
        return users.stream()
                .filter(user -> id == user.getId())
                .findFirst()
                .orElseThrow( ()
                        -> new UserNotFoundException(String.format("user with id- [%d] does not exists ",id)));
    }

    public Post savePost(int userId, Post post){
        if(null == post.getId()){
            post.setId(++postCounter);
        }
        if(null == post.getUserId()){
            post.setUserId(userId);
        }
        posts.add(post);
        return post;
    }

    public List<Post> findAllPosts(Integer userId){
        return  posts.stream().filter(post -> post.getUserId() == userId).collect(Collectors.toList());
    }

    public Post findOnePost(Integer userId, Integer postId){
        return  posts.stream()
                .filter(post -> post.getUserId().equals(userId))
                .filter(post -> post.getId().equals(postId))
                .findFirst().
                        orElse(null);
    }

}
