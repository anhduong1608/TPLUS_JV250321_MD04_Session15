package business;

import dao.MovieDAO;
import dao.MovieDAOImpl;
import entity.Movie;

import java.util.List;

public class MovieBusinessImpl implements MovieBusiness {
    private MovieDAO movieDAO = new MovieDAOImpl();

    @Override
    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.listMovies();
    }

    @Override
    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieDAO.deleteMovie(id);
    }
}

