package com.bahngFamily.jihoon.springboot.web.dto;

import com.bahngFamily.jihoon.springboot.domain.posts.Posts;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
  private Long id;
  private String title;
  private String content;
  private String author;
  private LocalDateTime modifiedDate;

  public PostsListResponseDto(Posts entity){
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }
}
