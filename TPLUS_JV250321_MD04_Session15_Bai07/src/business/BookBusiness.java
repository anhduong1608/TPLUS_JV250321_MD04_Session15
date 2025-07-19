package business;

import java.util.Scanner;

public interface BookBusiness {
    void createBook(Scanner scanner);

    void updateBook(Scanner scanner);

    void deleteBook(Scanner scanner);

    void displayListBook();

    void searchBooksByAuthor(Scanner scanner);
}
