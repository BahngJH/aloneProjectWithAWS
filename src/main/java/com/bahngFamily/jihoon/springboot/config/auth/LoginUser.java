package com.bahngFamily.jihoon.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치를 지정합니다. PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다.
//이 외에도 클래스 선언문에 쓸 수 있는 Type 등이 있습니다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
  //@interface : 이 파일을 어노테이션 클래스로 지정합니다. LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면됨


  //그리고 같은 위치에 LoginUserArgumentResolver를 생성합니다. HandlerMethodArgumentResolver 인터페이스를 구현한 클래스죠.
  //HandlerMethodArgumentResolver는 한 가지 기능을 지원합니다. 조건에 맞는 경우 메소드가 있다면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로
  //해당 메소드의 파라미터로 넘길 수 있습니다.
}
