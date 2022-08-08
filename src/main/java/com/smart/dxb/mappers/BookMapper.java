package com.smart.dxb.mappers;

import com.smart.dxb.dto.BookDTO;
import com.smart.dxb.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    //@Mapping(source = "bookId", target = "bookDTO.bookId")
    BookDTO toBookDTO(Book book);

    Book toBook(BookDTO bookDTO);

    List<BookDTO> toBookDTOs(List<Book> books);

}
