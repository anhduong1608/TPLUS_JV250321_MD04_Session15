package dao;

import entity.Movie;
import java.util.List;

public interface MovieDAO {
    void addMovie(Movie movie);
    List<Movie> listMovies();
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}
