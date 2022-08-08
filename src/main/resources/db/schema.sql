
create table if not exists BOOK (
    book_id INT AUTO_INCREMENT primary key,
    book_name varchar(200),
    description varchar(1000),
    book_author varchar(200),
    classification varchar(100),
    book_price DOUBLE,
    isbn varchar(100)
);
CREATE SEQUENCE seq_book START WITH 6 INCREMENT BY 1; -- starts with 6, since there is already 5 records added through data.sql


create table if not exists BOOK_PROMOTION (
    promotion_id INT AUTO_INCREMENT primary key,
    promotion_code varchar(100),
    classification varchar(100),
    discount double,
    is_active boolean
);
CREATE SEQUENCE seq_book_promotion START WITH 3 INCREMENT BY 1; -- starts with 3, since there is already 5 records added through data.sql

