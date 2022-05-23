package com.cos.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// 원래는 @Repository를 적어야 스프링 IoC에 빈 등록이 되는데
// JpaRepository를 extends하면 생략 가능함.
// JpaRepository는 CRUD 함수를 들고 있음.
public interface BookRepository extends JpaRepository<Book, Long>{

}
