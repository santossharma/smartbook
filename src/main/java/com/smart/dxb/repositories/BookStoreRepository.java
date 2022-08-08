package com.smart.dxb.repositories;

import com.smart.dxb.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Repository
public interface BookStoreRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByClassification(String classification);

    Book findByBookName(String bookName);
    Book findByIsbn(String isbn);

    Book findByBookId(Integer bookId);
    List<Book> findAllByBookAuthor(String bookAuthor);





}
