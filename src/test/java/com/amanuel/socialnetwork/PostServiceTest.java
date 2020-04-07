package com.amanuel.socialnetwork;

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

    @Test
    void getAllToDos(){
        Post postSample = new Post(1,"I love the weather today.",new Date());
        postRepository.save(postSample);
        PostService postService = new PostService(postRepository);

        Post firstResult = postService.findAll().get(0);

        Assertions.assertEquals(postSample.getText(), firstResult.getText());
        Assertions.assertEquals(postSample.getDate(), firstResult.getDate());
        Assertions.assertEquals(postSample.getId(), firstResult.getId());

//        List<Post> postList = (List<Post>) postService.findAll();
//        Post lastToDo = postList.get(postList.size()-1);
//
//        Assertions.assertEquals(postSample.getId(), lastToDo.getId());
//        Assertions.assertEquals(postSample.getText(), lastToDo.getText());
//        Assertions.assertEquals(postSample.getDate(), lastToDo.getDate());
    }
}
