package com.bahngFamily.jihoon.springboot.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

//여기 테스트 부분 에러 났었는데 문제 원인은 저자가 집필 당시엔 그래들4였고 그 버전대로 하다보니
//그래들5인 나같은 사람들은 오류가 발생 서치해보니 그래들을 다운드레이드 해야했음.
//cd D:\gitFolder\com.bahngFamily.jihoon => git bash here로 gradlew wrapper --gradle-version 4.10.2 명령어 입력
public class HelloResponseDtoTest {

  @Test
  public void 롬복_기능_테스트(){
    //given
    String name = "test";
    int amount = 1000;

    //when
    HelloResponseDto dto = new HelloResponseDto(name, amount);

    //then
    assertThat(dto.getName()).isEqualTo(name);
    assertThat(dto.getAmount()).isEqualTo(amount);

    //assertj라는 테스트 검증 라이브러리의 검증 메소드입니다.
    // 메소드 체이닝이 지원되어 메소드를 이어서 사용가능
  }
}
