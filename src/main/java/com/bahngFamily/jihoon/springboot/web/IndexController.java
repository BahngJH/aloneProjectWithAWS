package com.bahngFamily.jihoon.springboot.web;

import com.bahngFamily.jihoon.springboot.config.auth.LoginUser;
import com.bahngFamily.jihoon.springboot.config.auth.dto.SessionUser;
import com.bahngFamily.jihoon.springboot.service.posts.PostsService;
import com.bahngFamily.jihoon.springboot.web.dto.PostsResponseDto;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

  private final PostsService postsService;
  private final HttpSession httpSession;

  //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 프리픽스, 서픽스가 자동으로 지정됨
  // main/java/resources/template에서 반환되는 문자열과 일치하는 머스테치 파일을 ViewResolver가 처리
  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user){
    model.addAttribute("posts", postsService.findAllDesc());

    if(user != null)
      model.addAttribute("userName", user.getName());

    return "index";
  }

  @GetMapping
  public String postsSave(){
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model){
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post", dto);
    return "posts-update";
  }
}
