package entity;

import validator.Validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.Scanner;

public class Book {
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private int bookPublishedYear;
    private BigDecimal bookPrice;

    public Book() {
    }

    public Book(int bookId, String bookTitle, String bookAuthor, int bookPublishedYear, BigDecimal bookPrice) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublishedYear = bookPublishedYear;
        this.bookPrice = bookPrice;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPublishedYear() {
        return bookPublishedYear;
    }

    public void setBookPublishedYear(int bookPublishedYear) {
        this.bookPublishedYear = bookPublishedYear;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void inputData(Scanner scanner) {
        this.bookTitle = inputTitle(scanner);
        this.bookAuthor = inputAuthor(scanner);
        this.bookPublishedYear = inputPublishedYear(scanner);
        this.bookPrice = inputPrice(scanner);
    }

    public String inputTitle(Scanner scanner) {
        final int TITLE_MAX_LENGTH = 255;
        do {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            if (Validation.isValidLength(title, TITLE_MAX_LENGTH)) {
                return title;
            } else {
                System.err.println("This field can not be empty");
            }
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        final int AUTHOR_MAX_LENGTH = 255;
        do {
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            if (Validation.isValidLength(author, AUTHOR_MAX_LENGTH)) {
                return author;
            } else {
                System.err.println("This field can not be empty");
            }
        } while (true);
    }

    public int inputPublishedYear(Scanner scanner) {
        do {
            System.out.print("Enter published year (yyyy): ");
            String year = scanner.nextLine();
            if (Validation.isPositiveInteger(year)) {
                return Integer.parseInt(year);
            } else {
                System.err.println("Invalid year format. Please enter a 4-digit year");
            }
        } while (true);
    }

    public BigDecimal inputPrice(Scanner scanner) {
        do {
            System.out.print("Enter book price (decimal(10,2)): ");
            String price = scanner.nextLine();
            if (Validation.isValidDecimal(price, 10, 2)) {
                return new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
            } else {
                System.err.println("Invalid price format. Please enter a decimal(10,2)");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Title: %s - Author: %s - Published Year: %s - Price: %.2f",
                this.bookId, this.bookTitle, this.bookAuthor, this.bookPublishedYear, this.bookPrice);
    }
}
