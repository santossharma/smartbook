package com.smart.dxb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @SequenceGenerator(name = "book_seq_id_generator", sequenceName = "seq_book", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_id_generator")
    private Integer bookId;
    private String bookName;
    private String description;
    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author bookAuthor;*/
    private String bookAuthor;
    private String classification;
    private double bookPrice;
    private String isbn;

}
