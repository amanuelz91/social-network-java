package com.amanuel.socialnetwork;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;



@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void tearDown(){
        postRepository.deleteAll();
    }

    @Test
    void getAllPosts(){
        Post postSample = new Post(1,"I love the weather today.",new Date());
        postRepository.save(postSample);
        PostService postService = new PostService(postRepository);

        Post firstResult = postService.findAll().get(0);

        Assertions.assertEquals(postSample.getText(), firstResult.getText());
        Assertions.assertEquals(postSample.getDate(), firstResult.getDate());
        Assertions.assertEquals(postSample.getId(), firstResult.getId());

    }

    @Test
    void savePost(){
        PostService postService = new PostService(postRepository);
        Post postSample = new Post("I love the weather today.",new Date());

        postService.save(postSample);

        Assertions.assertEquals(1.0, postRepository.count());
    }
}
