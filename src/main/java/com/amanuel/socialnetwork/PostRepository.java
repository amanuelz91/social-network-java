package com.amanuel.socialnetwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post,Long>{

    @Query("SELECT p FROM Post p where p.userID = :userID")
    public Optional<List<Post>> findPostsByUserID(String userID);

}
