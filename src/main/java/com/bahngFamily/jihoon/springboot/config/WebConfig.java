package com.bahngFamily.jihoon.springboot.config;

import com.bahngFamily.jihoon.springboot.config.auth.LoginUserArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {                    //스프링에서 인시될 수 있도록 WebMvcConfigurer에 추가
  private final LoginUserArgumentResolver loginUserArgumentResolver;

  @Override //HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야합니다.
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
    argumentResolvers.add(loginUserArgumentResolver);
  }
}
