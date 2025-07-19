package dao;

import entity.Book;

import java.util.List;

public interface BookDAO {
    boolean addBook(Book book);

    Book findBookById(int bookID);

    boolean updateBook(Book book);

    boolean deleteBook(int bookId);

    List<Book> getAllBooks();

    List<Book> findBooksByAuthor(String author);

    Book findBooksByTitleAndAuthor(String title, String author);
}
