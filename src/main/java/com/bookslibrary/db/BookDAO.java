package com.bookslibrary.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookslibrary.model.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {

}
