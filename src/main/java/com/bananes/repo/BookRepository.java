package com.bananes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bananes.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
