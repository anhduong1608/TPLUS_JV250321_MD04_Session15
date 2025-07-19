package business.imp;

import business.BookBusiness;
import dao.BookDAO;
import dao.imp.BookDAOImp;
import entity.Book;
import validator.Validation;

import java.util.List;
import java.util.Scanner;

public class BookBusinessImp implements BookBusiness {
    private final BookDAO bookDAO;

    public BookBusinessImp() {
        bookDAO = new BookDAOImp();
    }

    @Override
    public void createBook(Scanner scanner) {
        Book newBook = new Book();
        newBook.inputData(scanner);
        Book book = bookDAO.findBooksByTitleAndAuthor(newBook.getBookTitle(), newBook.getBookAuthor());
        if (book != null) {
            if (bookDAO.addBook(newBook)) {
                System.out.println("Add successful");
            } else {
                System.out.println("Add fail!");
            }
        } else {
            System.out.println("This book is not exist");
        }
    }

    @Override
    public void updateBook(Scanner scanner) {
        System.out.print("Enter book ID to update: ");
        String bookId = scanner.nextLine();
        if (Validation.isPositiveInteger(bookId)) {
            Book book = bookDAO.findBookById(Integer.parseInt(bookId));
            if (book != null) {
                book.setBookTitle(book.inputTitle(scanner));
                book.setBookAuthor(book.inputAuthor(scanner));
                book.setBookPublishedYear(book.inputPublishedYear(scanner));
                book.setBookPrice(book.inputPrice(scanner));
                if (bookDAO.updateBook(book)) {
                    System.out.println("Update successful");
                } else {
                    System.out.println("Update fail!");
                }
            } else {
                System.out.println("This book is not exist!");
            }
        } else {
            System.out.println("Enter a positive integer!");
        }
    }

    @Override
    public void deleteBook(Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        String bookId = scanner.nextLine();
        if (Validation.isPositiveInteger(bookId)) {
            Book book = bookDAO.findBookById(Integer.parseInt(bookId));
            if (book != null) {
                if (bookDAO.deleteBook(Integer.parseInt(bookId))) {
                    System.out.println("Update successful");
                } else {
                    System.out.println("Update fail!");
                }
            } else {
                System.out.println("This book is not exist!");
            }
        } else {
            System.out.println("Enter a positive integer!");
        }
    }

    @Override
    public void displayListBook() {
        List<Book> bookList = bookDAO.getAllBooks();
        if (!bookList.isEmpty()) {
            System.out.println("List Book:");
            bookList.forEach(System.out::println);
        } else {
            System.out.println("List book is empty");
        }
    }

    @Override
    public void searchBooksByAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        if (Validation.isNotEmpty(author)) {
            List<Book> bookList = bookDAO.findBooksByAuthor(author);
            if (!bookList.isEmpty()) {
                System.out.printf("List Book of author '%s':\n", author);
                bookList.forEach(System.out::println);
            } else {
                System.out.println("List book is empty");
            }
        } else {
            System.out.println("This field can not be empty!");
        }
    }
}
