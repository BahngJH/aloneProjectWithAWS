package com.bahngFamily.jihoon.springboot.domain.posts;

import com.bahngFamily.jihoon.springboot.domain.BaseTimeEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //기본 생성자 자동추가
@Entity //테이블과 링크될 클래스임을 명시(필수), 카멜케이스로 이름을 언더스코어 네이밍으로 이름을 매칭함
public class Posts extends BaseTimeEntity {
  @Id   //PK 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙을 지정, 부트 2.0부턴 GenerationType.IDENTITY이 있어야 auto_increment가 됨
  private Long id;

  @Column(length = 500, nullable = false)   //테이블 컬럼을 나타내며 굳이 선언 안해도 해당 클래스의 필드는 모두 컬럼이 됨
  private String title;                     //하지만 사용하는 이유는 기본값 외에 추가로 변경이 필요하면 사용합니다.
                                            //ex) 문자열 기본값은 255 사이즈인데 500으로 변경하거나 타입을 TEXT로 바꾸고 싶은 경우
  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  private String author;

  @Builder
  public Posts(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content){
    this.title = title;
    this.content = content;
  }
}
