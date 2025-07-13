package dao;

import entity.Movie;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public void addMovie(Movie movie) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL add_movie(?, ?, ?)}");
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setInt(3, movie.getReleaseYear());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error adding movie: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> listMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL list_movies()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movie m = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("release_year")
                );
                movies.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error listing movies: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL update_movie(?, ?, ?, ?)}");
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getReleaseYear());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error updating movie: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovie(int id) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{CALL delete_movie(?)}");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Error deleting movie: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

