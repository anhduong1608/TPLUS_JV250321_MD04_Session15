package presentation;

import business.MovieBusiness;
import business.MovieBusinessImpl;
import entity.Movie;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieBusiness movieBusiness = new MovieBusinessImpl();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n******QUẢN LÝ PHIM******");
            System.out.println("1.Thêm phim");
            System.out.println("2.Liệt kê phim");
            System.out.println("3.Sửa phim");
            System.out.println("4.Xoá phim");
            System.out.println("5.Thoát");
            System.out.print("lựa chọn chức năng: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập tiêu đề: ");
                    String title = scanner.nextLine();
                    System.out.print("Nhập đạo diễn: ");
                    String director = scanner.nextLine();
                    System.out.print("Nhập năm phát hành: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    movieBusiness.addMovie(new Movie(0, title, director, year));
                    break;
                case 2:
                    List<Movie> movies = movieBusiness.getAllMovies();
                    movies.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Nhập ID phim cần sửa: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập tiêu đề mới: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Nhập đạo diễn mới: ");
                    String newDirector = scanner.nextLine();
                    System.out.print("Nhập năm phát hành mới: ");
                    int newYear = Integer.parseInt(scanner.nextLine());
                    movieBusiness.updateMovie(new Movie(updateId, newTitle, newDirector, newYear));
                    break;
                case 4:
                    System.out.print("Nhập ID phim cần xoá: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    movieBusiness.deleteMovie(deleteId);
                    break;
                case 5:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 5);
        scanner.close();
    }
}

