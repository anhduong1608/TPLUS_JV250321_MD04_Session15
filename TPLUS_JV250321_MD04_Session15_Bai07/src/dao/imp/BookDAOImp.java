package dao.imp;

import dao.BookDAO;
import entity.Book;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements BookDAO {
    @Override
    public boolean addBook(Book book) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call add_book(?, ?, ?, ?)}");
            callableStatement.setString(1, book.getBookTitle());
            callableStatement.setString(2, book.getBookAuthor());
            callableStatement.setInt(3, book.getBookPublishedYear());
            callableStatement.setBigDecimal(4, book.getBookPrice());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public Book findBookById(int bookID) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Book book = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call find_book_by_id(?)}");
            callableStatement.setInt(1, bookID);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setBookId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setBookPublishedYear(resultSet.getInt("published_year"));
                book.setBookPrice(resultSet.getBigDecimal("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return book;
    }

    @Override
    public boolean updateBook(Book book) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call update_book(?,?,?,?,?)}");
            callableStatement.setInt(1, book.getBookId());
            callableStatement.setString(2, book.getBookTitle());
            callableStatement.setString(3, book.getBookAuthor());
            callableStatement.setInt(4, book.getBookPublishedYear());
            callableStatement.setBigDecimal(5, book.getBookPrice());
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call delete_book(?)}");
            callableStatement.setInt(1, bookId);
            callableStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Book> bookList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call show_all_books}");
            ResultSet resultSet = callableStatement.executeQuery();
            bookList = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setBookPublishedYear(resultSet.getInt("published_year"));
                book.setBookPrice((resultSet.getBigDecimal("price")));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return bookList;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        List<Book> bookList = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call find_books_by_author(?)}");
            callableStatement.setString(1, author);
            ResultSet resultSet = callableStatement.executeQuery();
            bookList = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setBookPublishedYear(resultSet.getInt("published_year"));
                book.setBookPrice((resultSet.getBigDecimal("price")));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return bookList;
    }

    @Override
    public Book findBooksByTitleAndAuthor(String title, String author) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Book book = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call find_books_by_title_and_author(?,?)}");
            callableStatement.setString(1, title);
            callableStatement.setString(2, author);
            ResultSet resultSet = callableStatement.executeQuery();
            book = new Book();
            if (resultSet.next()) {
                book.setBookId(resultSet.getInt("id"));
                book.setBookTitle(resultSet.getString("title"));
                book.setBookAuthor(resultSet.getString("author"));
                book.setBookPublishedYear(resultSet.getInt("published_year"));
                book.setBookPrice((resultSet.getBigDecimal("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return book;
    }
}
