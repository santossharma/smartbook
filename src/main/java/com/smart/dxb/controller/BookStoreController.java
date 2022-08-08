package com.smart.dxb.controller;

import com.smart.dxb.constants.AssertMessages;
import com.smart.dxb.dto.BookDTO;
import com.smart.dxb.dto.BookInvoiceDTO;
import com.smart.dxb.entities.Book;
import com.smart.dxb.services.BookStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@RestController
@RequestMapping(name = "/v1/book")
@Api(value = "Book Store Resource")
@Slf4j
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Saves New Book to the Book Store.")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Successful Saved Book"),
            @ApiResponse(code = 201, message = "Successful Saved Book")
        }
    )
    public ResponseEntity<BookDTO> saveBookInStore(@RequestBody @Valid BookDTO bookDTO) {
        BookDTO savedBook = bookStoreService.saveBookInStore(bookDTO);

        return ResponseEntity.created(URI.create("/v1/book/" + savedBook.getBookId() + "/detail")).body(savedBook);
    }

    @GetMapping("/{bookId}/detail")
    @ApiOperation(value = "Searches book by book id")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Book found"),
            @ApiResponse(code = 400, message = "Bad Request")
        }
    )
    public ResponseEntity<BookDTO> getBookDetail(@PathVariable final Integer bookId) {
        BookDTO bookDetail = bookStoreService.getBookDetail(bookId);

        return ResponseEntity.ok(bookDetail);
    }

    @PutMapping("/{bookId}")
    @ApiOperation(value = "Updates book by book id.")
    public ResponseEntity<BookDTO> updateBook(@PathVariable final Integer bookId, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookStoreService.updateBookDetail(bookId, bookDTO);

        return ResponseEntity.ok(updatedBook);
    }

    @PutMapping("isbn/{isbn}")
    @ApiOperation(value = "Updates book by isbn.")
    public ResponseEntity<BookDTO> updateBookByISBN(@PathVariable final String isbn, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookStoreService.updateBookDetailByISBN(isbn, bookDTO);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation(value = "Deletes book by book id.")
    public ResponseEntity<Book> deleteBook(@PathVariable final Integer bookId) {

        bookStoreService.deleteBook(bookId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{classification}")
    @ApiOperation(value = "Searches book by classification.")
    public ResponseEntity<List<BookDTO>> searchByClassification(@PathVariable String classification) {

        List<BookDTO> bookResponse = null;
        if (Optional.ofNullable(classification).isPresent()) {
            bookResponse = bookStoreService.searchBooksByClassification(classification);
        }

        return ResponseEntity.ok().body(bookResponse);
    }

    @GetMapping("/search/{isbn}")
    @ApiOperation(value = "This method is used to get books by classification/parameter")
    public ResponseEntity<BookDTO> searchByISBN(@PathVariable final String isbn) {

        BookDTO book = null;
        if (Optional.ofNullable(isbn).isPresent()) {
            book = bookStoreService.searchBooksByISBN(isbn);
        }

        return ResponseEntity.ok().body(book);
    }

    @GetMapping("/search/{author}")
    @ApiOperation(value = "Searches book by Author")
    public ResponseEntity<List<BookDTO>> searchByAuthor(@PathVariable final String author) {

        List<BookDTO> books = null;
        if (Optional.ofNullable(author).isPresent()) {
            books = bookStoreService.getBooksByAuthor(author);
        }

        return ResponseEntity.ok().body(books);
    }

    @PostMapping("/checkout")
    @ApiOperation(value = "Calculates payable amount during checkout.")
    public ResponseEntity<BookInvoiceDTO> checkoutBook(@RequestBody List<BookDTO> books,
                    @RequestParam(name = "promotionCode", defaultValue = "NA") String promotionCode) {

        log.info("Checkout with books {}",books);
        Assert.notNull(books, AssertMessages.BOOKS_MANDATORY);

        BookInvoiceDTO bookInvoice = BookInvoiceDTO.builder()
                .books(books)
                .totalPayableAmount(bookStoreService.checkout(books, promotionCode))
                .build();

        return ResponseEntity.ok().body(bookInvoice);
    }

}
