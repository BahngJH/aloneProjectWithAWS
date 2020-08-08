package com.bahngFamily.jihoon.springboot.service.posts;

import com.bahngFamily.jihoon.springboot.domain.posts.Posts;
import com.bahngFamily.jihoon.springboot.domain.posts.PostsRepository;
import com.bahngFamily.jihoon.springboot.web.dto.PostsResponseDto;
import com.bahngFamily.jihoon.springboot.web.dto.PostsSaveRequestDto;
import com.bahngFamily.jihoon.springboot.web.dto.PostsUpdateRequestDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requsetDto){
    return postsRepository.save(requsetDto.toEntity()).getId();
  }

  //update 기능에서 DB에 쿼리를 날리는 부분이 없습니다. JPA의 영속성 컨텍스트 때문입니다. 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경입니다.
  //일종의 논리적 개념이고 JPA의 핵심 내용은 엔티티가 영속성 컨텍스트를 포함되어 있냐 없냐로 갈립니다.
  //JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태입니다.
  //이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영합니다.즉 Entity 객체 값만 바꾸면 별도로 update쿼리를 날릴 필요가 없다.
  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto){
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
    posts.update(requestDto.getTitle(), requestDto.getContent());
    return id;
  }

  @Transactional
  public PostsResponseDto findById(Long id){
    Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
    return new PostsResponseDto(entity);
  }
}
