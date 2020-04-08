package com.amanuel.socialnetwork;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void tearDown(){
//        postRepository.deleteAllInBatch();
//        postRepository.flush();
    }

    @Test
    void getAllPosts(){
        Post postSample = new Post(1,"I love the weather today.",new Date(),"1");
        postRepository.save(postSample);
        PostService postService = new PostService(postRepository);

        Post firstResult = postService.findAll().get(0);

        Assertions.assertEquals(postSample.getText(), firstResult.getText());
        Assertions.assertEquals(postSample.getDate(), firstResult.getDate());
        Assertions.assertEquals(postSample.getId(), firstResult.getId());
        Assertions.assertEquals(postSample.getUserID(), firstResult.getUserID());

    }

    @Test
    void getUserTimeline() throws ChangeSetPersister.NotFoundException {
        PostService postService = new PostService(postRepository);

        Post postSample1 = new Post(1,"Darn! We lost!",new Date(),"1");
        Post postSample2 = new Post(2,"Good game though.",new Date(),"1");
        Post postSample3 = new Post(3,"I love the weather today.",new Date(),"2");

        List<Post> postsList = new ArrayList<>();
        postsList.add(postSample1);
        postsList.add(postSample2);

        postRepository.save(postSample1);
        postRepository.save(postSample2);
        postRepository.save(postSample3);

        Optional<List<Post>> results = postService.findPostsByUserID("1");
        List<Post> resultsList = results.orElseThrow(ChangeSetPersister.NotFoundException::new);

        int timelineLength = resultsList.size();
//        System.out.println("#########################resultsList#########################");
//        System.out.println(resultsList.get(0).getId());
//        System.out.println("#########################-----------#########################");
        // Personal timeline for user 1 should only show user 1's posts
        Assertions.assertEquals(2,timelineLength);

        Post firstResult = resultsList.get(0);
        Post secondResult = resultsList.get(1);

        Assertions.assertEquals(postSample1.getText(), firstResult.getText());
        Assertions.assertEquals(postSample1.getDate(), firstResult.getDate());
        Assertions.assertEquals(postSample1.getId(), firstResult.getId());
        Assertions.assertEquals(postSample1.getUserID(), firstResult.getUserID());

        Assertions.assertEquals(postSample2.getText(), secondResult.getText());
        Assertions.assertEquals(postSample2.getDate(), secondResult.getDate());
        Assertions.assertEquals(postSample2.getId(), secondResult.getId());
        Assertions.assertEquals(postSample2.getUserID(), secondResult.getUserID());

    }

    @Test
    void savePost(){
        PostService postService = new PostService(postRepository);
        Post postSample = new Post("I love the weather today.",new Date(),"1");

        postService.save(postSample);

        Assertions.assertEquals(1.0, postRepository.count());
    }
}
