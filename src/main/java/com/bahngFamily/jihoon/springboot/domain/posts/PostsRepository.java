package com.bahngFamily.jihoon.springboot.domain.posts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {
  @Query("SELECT p FROM Posts p ORDER BY p.id desc")
  List<Posts> findAllDesc();
}
