package com.example.spring.rest.controller;

import com.example.spring.rest.dao.UserService;
import com.example.spring.rest.dto.Post;
import com.example.spring.rest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
  private UserService service ;

    @GetMapping
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path = "/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }

    /**
     * @param user
     * @return StatusCode created with the resource URI of newly created User
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{userId}/posts")
    public List<Post> retrieveAllPostsFor(@PathVariable int userId){
        return service.findAllPosts(userId);
    }

    @PostMapping(path = "/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int userId, @RequestBody Post post)
    {
        Post savedPost = service.savePost(userId, post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{userId}/posts/{postId}")
    public Post retrieveOnePostsFor(@PathVariable int userId, @PathVariable int postId){
        return service.findOnePost(userId,postId);
    }

}
