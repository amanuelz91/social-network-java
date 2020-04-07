package com.amanuel.socialnetwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post,Long>{

}
