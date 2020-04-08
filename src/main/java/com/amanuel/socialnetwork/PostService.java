package com.amanuel.socialnetwork;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<List<Post>> findPostsByUserID(String userID) {
        Post postSample = new Post("I love the weather today.",new Date(),userID);
        return  postRepository.findPostsByUserID(userID);
    }
}
