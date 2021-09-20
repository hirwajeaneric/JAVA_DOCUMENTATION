CREATE TABLE Book(
    bookid VARCHAR(30) PRIMARY KEY NOT NULL,
    bookcategory VARCHAR(30),
    title VARCHAR(30),
    publishinghouse VARCHAR(30),
    dateofpublication date,
    author VARCHAR(30),
    pages int,
    numberofcopies int,
    CONSTRAINT fk_book_category FOREIGN KEY (bookcategory) 
    REFERENCES BookCategory (categoryid) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

CREATE TABLE BookCategory(
    categoryid VARCHAR(30) PRIMARY KEY NOT NULL,
    categoryname VARCHAR(30)
);
