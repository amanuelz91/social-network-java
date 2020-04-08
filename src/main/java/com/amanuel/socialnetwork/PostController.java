package com.amanuel.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/timeline")
    ResponseEntity<List<Post>> getAllPostsForUser(@RequestParam String userID) {
//    ResponseEntity<Post> getAllPostsForUser() {
        return new ResponseEntity(postService.findPostsByUserID(userID), HttpStatus.OK);
    }

    @PostMapping("/posts")
    ResponseEntity<Post> create(@RequestBody Post post) {
        return new ResponseEntity(postService.save(post), HttpStatus.CREATED);
    }
}
