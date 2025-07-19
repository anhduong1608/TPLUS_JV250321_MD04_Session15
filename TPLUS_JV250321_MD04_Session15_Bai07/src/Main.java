import business.BookBusiness;
import business.imp.BookBusinessImp;
import validator.Validation;

import java.util.Scanner;

public class Main {
    private final BookBusiness bookBusiness;

    public Main() {
        bookBusiness = new BookBusinessImp();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("************** BOOK MANAGEMENT *****************");
            System.out.println("1. Add book");
            System.out.println("2. Update book");
            System.out.println("3. Delete book");
            System.out.println("4. Search book by author");
            System.out.println("5. Display all books");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.bookBusiness.createBook(scanner);
                    case 2 -> main.bookBusiness.updateBook(scanner);
                    case 3 -> main.bookBusiness.deleteBook(scanner);
                    case 4 -> main.bookBusiness.searchBooksByAuthor(scanner);
                    case 5 -> main.bookBusiness.displayListBook();
                    default -> System.exit(0);
                }
            } else {
                System.err.println("Please enter a positive integer between 1-6");
            }
        } while (true);
    }
}
