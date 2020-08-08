package com.bahngFamily.jihoon.springboot.web.dto;

import com.bahngFamily.jihoon.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
  private String title;
  private String content;
  private String author;

  @Builder
  public PostsSaveRequestDto(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  //여기서 Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했습니다. 하지만 절대로 Entity클래스를 Request/Response 클래스로
  //사용해서는 안됩니다. Entity클래스는 데이터베이스와 맞닿는 핵심 클래스입니다. Entity클래스 기준으로 테이블이 생성되고
  //스키마가 변경됩니다. 화면 변경은 아주 사소한 기능인데 이를 위해 테이블을 만들어서 안됩니다.
  public Posts toEntity(){
    return Posts.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
  }
}
