package com.amanuel.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    ResponseEntity<List<Post>> getAllToDos() {
        return new ResponseEntity(postService.findAll(), HttpStatus.OK);
    }
}
