package com.bahngFamily.jihoon.springboot.web;

import com.bahngFamily.jihoon.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class indexController {

  private final PostsService postsService;

  //머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 프리픽스, 서픽스가 자동으로 지정됨
  // main/java/resources/template에서 반환되는 문자열과 일치하는 머스테치 파일을 ViewResolver가 처리
  @GetMapping("/")
  public String index(Model model){
    model.addAttribute("posts", postsService.findAllDesc());
    return "index";
  }

  @GetMapping
  public String postsSave(){
    return "posts-save";
  }
}
