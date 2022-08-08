insert into BOOK (book_id, book_name, description, book_author, classification, book_price, isbn)
    values (1, 'System Design Interview', 'System Design Interview. It covers a different set of system design interview questions and solutions',
    'Alex Xu', 'SOFTWARE', 200.00, '979-8664653403');

insert into BOOK (book_id, book_name, description, book_author, classification, book_price, isbn)
    values (2, 'Great Circle',
    'NEW YORK TIMES BESTSELLER. The unforgettable story of a daredevil female aviator determined to chart her own course in life, at any cost: an “epic trip—through Prohibition and World War II, from Montana to London to present-day Hollywood—and you’ll relish every minute',
    'Maggie Shipstead', 'FICTION', 13.00, '0525656979');

insert into BOOK (book_id, book_name, description, book_author, classification, book_price, isbn)
    values (3, 'The Life of the Mind',
    'The contemporary fiction landscape is full of protagonists like Christine Smallwood Dorothy: white millennial women who are grappling with their privilege and existence in a world that constantly feels like it on the verge of collapse.',
    'Christine Smallwood', 'FICTION', 12.27, '0593229894');

insert into BOOK (book_id, book_name, description, book_author, classification, book_price, isbn)
    values (4, 'Superman: Action Comics Vol. 3 : Men of Steel (Rebirth)',
    'The smash-hit Rebirth of Superman continues, from classic Superman writer Dan Jurgens and artists Tyler Kirkham, Patrick Zircher and Stephen Segovia. Lex Luthor says he’s a hero. He’s even taken to wearing the Superman S” on his armor.',
    'Kelly Barnhill', 'COMIC', 12.0, '1401273572');

insert into BOOK (book_id, book_name, description, book_author, classification, book_price, isbn)
    values (5, 'The Girl Who Drank the Moon',
    'This beautifully written, darkly funny coming-of-age story will enchant and entertain Daily Mail Every year, the people of the Protectorate leave a baby as an offering to the witch who lives in the forest.',
    'Kelly Barnhill', 'COMIC', 15.0, '978-1848126473');


-- Book Promotions
insert into BOOK_PROMOTION (promotion_id, promotion_code, classification, discount, is_active)
    values (1, 'DXBDSSBOOK001X', 'FICTION', 10, 1);
insert into BOOK_PROMOTION (promotion_id, promotion_code, classification, discount, is_active)
    values (2, 'DXBDSSELEC001X', 'LAPTOP', 15, 1);
