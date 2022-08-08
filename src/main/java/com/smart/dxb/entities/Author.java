package com.smart.dxb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

/*@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")*/
public class Author {
    /*@Id
    @SequenceGenerator(name = "author_seq_id_generator", sequenceName = "author_book", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_id_generator")
    private Integer authorId;
    private String authorFullName;
    private Integer rating;
    @OneToMany(mappedBy = "bookAuthor",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Book> books;*/

}
