package com.amanuel.socialnetwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PostsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostService postService;


    @Test
    void getAllPosts() throws Exception {
        List<Post> postServiceList = new ArrayList<>();
        postServiceList.add(new Post(1,"I love the weather today.", new Date(), "1"));

        when(postService.findAll()).thenReturn(postServiceList);
        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }

    @Test
    void getUserTimeline() throws Exception {
        List<Post> postServiceList = new ArrayList<>();
        postServiceList.add(new Post(1,"Darn! We lost!", new Date(), "1"));
        postServiceList.add(new Post(2,"Good game though.", new Date(), "1"));

        when(postService.findPostsByUserID("1")).thenReturn(java.util.Optional.of(postServiceList));
        mockMvc.perform(MockMvcRequestBuilders.get("/timeline").param("userID","1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void successfullyCreatePost() throws Exception {
        Date date = new Date();
        Post samplePost = new Post(1, "I love the weather today.", date, "1");
        when(postService.save(any(Post.class))).thenReturn(samplePost);
        ObjectMapper objectMapper = new ObjectMapper();
        String samplePostJSON = objectMapper.writeValueAsString(samplePost);

        ResultActions result = mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(samplePostJSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.text").value("I love the weather today."));
    }
}
