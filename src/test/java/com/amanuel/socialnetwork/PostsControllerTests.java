package com.amanuel.socialnetwork;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
    void getAllToDos() throws Exception {
        List<Post> postServiceList = new ArrayList<>();
        postServiceList.add(new Post(1,"I love the weather today.", new Date()));

        when(postService.findAll()).thenReturn(postServiceList);
        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(1))).andDo(print());
    }
}
