package com.bahngFamily.jihoon.springboot.domain;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어
// Entity들의 createDate, modifiedDate를 자동으로 관리하는 역할입니다.
@Getter
@MappedSuperclass                                 //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)    //BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
public abstract class BaseTimeEntity {
  @CreatedDate                                    //Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.
  private LocalDateTime createdDate;

  @LastModifiedDate                               //조회한 Entity값이 변경할 때 시간이 자동 저장됩니다.
  private LocalDateTime modifiedDate;
}
