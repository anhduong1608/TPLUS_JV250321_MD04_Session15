package business;

import entity.Movie;
import java.util.List;

public interface MovieBusiness {
    void addMovie(Movie movie);
    List<Movie> getAllMovies();
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}
