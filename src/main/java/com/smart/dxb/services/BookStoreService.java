package com.smart.dxb.services;

import com.smart.dxb.dto.BookDTO;
import com.smart.dxb.entities.Book;
import com.smart.dxb.entities.BookPromotion;
import com.smart.dxb.exception.ApplicationException;
import com.smart.dxb.mappers.BookMapper;
import com.smart.dxb.repositories.BookPromotionRepository;
import com.smart.dxb.repositories.BookStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Service
@Slf4j
public class BookStoreService {
    private final BookStoreRepository bookStoreRepository;
    private final BookPromotionRepository bookPromotionRepository;

    public BookStoreService(BookStoreRepository bookStoreRepository, BookPromotionRepository bookPromotionRepository) {
        this.bookStoreRepository = bookStoreRepository;
        this.bookPromotionRepository = bookPromotionRepository;
    }

    @Transactional
    public BookDTO saveBookInStore(BookDTO bookDTO) {

        Optional<Book> optionalUser = Optional.ofNullable(bookStoreRepository.findByBookName(bookDTO.getBookName()));
        if(optionalUser.isPresent()) {
            throw new ApplicationException("Book already exists", HttpStatus.BAD_REQUEST);
        }
        Book book = BookMapper.INSTANCE.toBook(bookDTO);

        Book savedBook = bookStoreRepository.save(book);
        log.info("Book saved successfully. Book Id {}",savedBook.getBookId());
        return BookMapper.INSTANCE.toBookDTO(savedBook);
    }

    public BookDTO getBookDetail(Integer bookId) {

        Book book = bookStoreRepository.findByBookId(bookId);
        if(Objects.isNull(book)) {
            log.info("Book does not exists against book id {}",bookId);
            throw new ApplicationException("Book does not exist", HttpStatus.BAD_REQUEST);
        }

        return BookMapper.INSTANCE.toBookDTO(book);
    }

    public BookDTO updateBookDetail(Integer bookId, BookDTO bookDTO) {
        log.info("Searching by Book Id {}",bookId);

        Book savedBook = getBookById(bookId);
        if(Objects.isNull(savedBook)) {
            log.info("Book does not exists against book id {}",bookId);
            throw new ApplicationException("Book does not exist", HttpStatus.BAD_REQUEST);
        }

        populateBookToUpdate(savedBook, bookDTO);

        return BookMapper.INSTANCE.toBookDTO(bookStoreRepository.save(savedBook));
    }

    public BookDTO updateBookDetailByISBN(String isbn, BookDTO bookDTO) {
        log.info("Searching by ISBN No {}",isbn);
        Book savedBook = bookStoreRepository.findByIsbn(isbn);

        if(Objects.isNull(savedBook)) {
            log.info("Book does not exists against ISBN No {}",isbn);
            throw new ApplicationException("Book does not exist", HttpStatus.BAD_REQUEST);
        }

        populateBookToUpdate(savedBook, bookDTO);

        return BookMapper.INSTANCE.toBookDTO(bookStoreRepository.save(savedBook));
    }

    public void deleteBook(Integer bookId) {
        Book savedBook = getBookById(bookId);
        if(Objects.isNull(savedBook)) {
            log.info("Book does not exists against book id {}",bookId);
            throw new ApplicationException("Book does not exist", HttpStatus.BAD_REQUEST);
        }

        bookStoreRepository.deleteById(bookId);
        log.info("Deleted Book Id {}",bookId);
    }

    private void populateBookToUpdate(Book savedBook, BookDTO bookDTO) {
        savedBook.setBookName(Optional.ofNullable(bookDTO.getBookName()).orElse(savedBook.getBookName()));
        savedBook.setBookAuthor(Optional.ofNullable(bookDTO.getBookAuthor()).orElse(savedBook.getBookAuthor()));
        savedBook.setDescription(Optional.ofNullable(bookDTO.getDescription()).orElse(savedBook.getDescription()));
        savedBook.setBookPrice(Optional.of(bookDTO.getBookPrice()).orElse(savedBook.getBookPrice()));
        savedBook.setIsbn(Optional.ofNullable(bookDTO.getIsbn()).orElse(savedBook.getIsbn()));
    }

    private Book getBookById(Integer bookId) {
        return bookStoreRepository.findById(bookId)
                .orElseThrow(() -> new ApplicationException("Book not found", HttpStatus.NOT_FOUND));
    }

    public List<BookDTO> searchBooksByClassification(String classification) {
        log.info("Search Books by Type {}",classification);
        List<Book> books = bookStoreRepository.findAllByClassification(classification);

        return BookMapper.INSTANCE.toBookDTOs(books);
    }

    public BookDTO searchBooksByISBN(String isbn) {
        log.info("Search Book by ISBN {}",isbn);
        return BookMapper.INSTANCE.toBookDTO(bookStoreRepository.findByIsbn(isbn));
    }

    public List<BookDTO> getBooksByAuthor(String author) {
        log.info("Search Book by Author {}",author);
        return BookMapper.INSTANCE.toBookDTOs(bookStoreRepository.findAllByBookAuthor(author));
    }

    public double checkout(List<BookDTO> books, String promotionCode) {

        List<Integer> bookIds = books.stream().map(BookDTO::getBookId).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(bookStoreRepository.findAllById(bookIds))) {
            BookPromotion bookPromotion = bookPromotionRepository.findByPromotionCodeAndIsActive(promotionCode, true);

            log.info("bookPromotion "+bookPromotion);

            if(Objects.nonNull(bookPromotion)) {
                return calculateDiscount(bookPromotion, books);
            }

            // Total book price without promotion applied
            return books.stream()
                    .mapToDouble(BookDTO::getBookPrice)
                    .sum();
        }
        throw new ApplicationException("Book does not exist", HttpStatus.BAD_REQUEST);
    }

    private static double calculateDiscount(BookPromotion bookPromotion, List<BookDTO> books) {
        Predicate<BookDTO> validPromotion = bookDTO -> (bookPromotion.getClassification().equalsIgnoreCase(bookDTO.getClassification()));
        Predicate<BookDTO> differentClassification = bookDTO -> (!bookPromotion.getClassification().equalsIgnoreCase(bookDTO.getClassification()));

        double totalBookPriceWithPromotionDiscount = books.stream()
                .filter(validPromotion)
                .mapToDouble(book -> (book.getBookPrice() - (book.getBookPrice() * bookPromotion.getDiscount()) / 100))
                .sum();

        double totalBookPriceWithoutPromotion = books.stream()
                .filter(differentClassification)
                .mapToDouble(BookDTO::getBookPrice)
                .sum();

        return totalBookPriceWithoutPromotion + totalBookPriceWithPromotionDiscount;
    }
}
