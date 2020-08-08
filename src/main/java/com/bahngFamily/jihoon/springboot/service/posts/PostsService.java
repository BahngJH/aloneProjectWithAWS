package com.bahngFamily.jihoon.springboot.service.posts;

import com.bahngFamily.jihoon.springboot.domain.posts.PostsRepository;
import com.bahngFamily.jihoon.springboot.web.dto.PostsSaveRequestDto;
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
}
