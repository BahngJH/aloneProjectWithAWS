package com.bahngFamily.jihoon.springboot.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bahngFamily.jihoon.springboot.domain.posts.Posts;
import com.bahngFamily.jihoon.springboot.domain.posts.PostsRepository;
import com.bahngFamily.jihoon.springboot.web.dto.PostsSaveRequestDto;
import com.bahngFamily.jihoon.springboot.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

  @Autowired
  private WebApplicationContext content;

  private MockMvc mvc;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @After
  public void tearDown() throws Exception {
    postsRepository.deleteAll();
  }

  @Before
  public void setup(){
    mvc = MockMvcBuilders
          .webAppContextSetup(content)
          .apply(springSecurity())
          .build();
  }

  @Test
  @WithMockUser(roles="USER")                         //인증된 모의 사용자를 만들어서 사용합니다. roles에 권한 추가 가능
  public void Posts_insert_등록된다() throws Exception {
    
    //given
    String title = "title";
    String content = "content";
    //String author = "author";
    PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).build();

    String url = "http://localhost:" + port +"/api/v1/posts";

    //when
    //ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
    mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(requestDto))).andExpect(status().isOk());

    //then
    //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    //assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  @WithMockUser(roles="USER")                         //인증된 모의 사용자를 만들어서 사용합니다. roles에 권한 추가 가능
  public void Posts_update_수정한다() throws Exception {
    //given
    Posts savedPosts = postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

    Long updateId = savedPosts.getId();
    String expectedTitle = "title2";
    String expectedContent = "content2";

    PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
        .title(expectedTitle)
        .content(expectedContent)
        .build();

    String url = "http://localhost:" + port + "/api/v1/posts/"+ updateId;

    HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    //when
    //ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
    mvc.perform(put(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requestDto))).andExpect(status().isOk());

    //then
    //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    //assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
  }

  @Test
  public void BaseTimeEntity_등록() {
    //given
    LocalDateTime now = LocalDateTime.of(2020,8,9,0,0,0);
    postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .build());
    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);

    System.out.println(">>>>>>>>>>> createDate= "+posts.getCreatedDate()+", modifiedDate: "+posts.getModifiedDate());

    assertThat(posts.getCreatedDate()).isAfter(now);
    assertThat(posts.getModifiedDate()).isAfter(now);

  }

}
