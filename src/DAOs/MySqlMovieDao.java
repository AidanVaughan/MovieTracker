/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

/**
 *
 * @author Administrator
 */
import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySqlMovieDao extends Daos.MySqlDao implements MovieDaoInterface {

    @Override

    public List<Movie> findAllMovies() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Movie> movies = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                plot = plot.replaceAll("\"", "*");
                plot = plot.trim();
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                Movie m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
        return movies;     // may be empty
    }

    @Override
    public Movie findMovieById(int mId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, mId);

            rs = ps.executeQuery();
            if (rs.next()) {
                int movieId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                plot = plot.replaceAll("\"", "*");
                plot = plot.trim();
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByYear() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByYear() " + e.getMessage());
            }
        }
        return m;     // m may be null 
    }

    public Movie findMovieByTitle(String mtitle) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, mtitle);

            rs = ps.executeQuery();
            if (rs.next()) {
                int movieId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                 plot = plot.replaceAll("\"", "*");
                plot = plot.trim();
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
            }
        } catch (SQLException e) {
            throw new DaoException("findMovieByYear() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findMovieByYear() " + e.getMessage());
            }
        }
        return m;     // m may be null 
    }

    @Override
    public List<Movie> findMovieByDirector(String mdirector) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM MOVIES WHERE director = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, mdirector);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();

            while (rs.next()) {
                int movieId = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String genre = rs.getString("GENRE");
                String director = rs.getString("DIRECTOR");
                String runtime = rs.getString("RUNTIME");
                String plot = rs.getString("PLOT");
                plot = plot.replaceAll("\"", "*");
                plot = plot.trim();
                String location = rs.getString("LOCATION");
                String poster = rs.getString("POSTER");
                String rating = rs.getString("RATING");
                String format = rs.getString("FORMAT");
                String year = rs.getString("YEAR");
                String starring = rs.getString("STARRING");
                int copies = rs.getInt("Copies");
                String barcode = rs.getString("BARCODE");
                String user_rating = rs.getString("USER_RATING");
                m = new Movie(movieId, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
                movies.add(m);

            }
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
        return movies;     
    }

    @Override
    public void addMovies() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = " insert into movies (title, genre, director ,"
                    + "runtime,plot,location,poster,rating,format,year,"
                    + "starring,copies,barcode,user_rating)"
                    + " values (?, ?, ?, ?, ? ,? ,? ,? ,?, ?, ? ,?,? ,?)";

            Scanner sc = new Scanner(System.in);

            System.out.println("Please enter the movie title");
            String title = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the movie genre");
            String genre = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the director");
            String director = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the runtime");
            String runtime = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the plot");
            String plot = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the location");
            String location = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the poster");
            String poster = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the rating");
            String rating = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the format");
            String format = sc.nextLine();
            System.out.println();

            System.out.println("Please enter year");
            String year = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the cast");
            String starring = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the amount of copies(must be an integer)");
            int copies = sc.nextInt();
            System.out.println();

            System.out.println("Please enter barcode");
            String barcode = sc.nextLine();
            System.out.println();

            System.out.println("Please enter the User Rating");
            String userRating = sc.nextLine();

            ps = con.prepareStatement(query);

            ps.setString(1, title);

            ps.setString(2, genre);

            ps.setString(3, director);

            ps.setString(4, runtime);

            ps.setString(5, plot);

            ps.setString(6, location);

            ps.setString(7, poster);

            ps.setString(8, rating);

            ps.setString(9, format);

            ps.setString(10, year);

            ps.setString(11, starring);

            ps.setInt(12, copies);

            ps.setString(13, barcode);

            ps.setString(14, userRating);

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }

    }

    @Override
    public void updateMovie() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = " update movies set title=?,director=?,year=? where title=?";

            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter what the title of the movie you want to update");
            String title = sc.nextLine();

            System.out.println("Please enter new title");
            String title1 = sc.nextLine();

            System.out.println("Please enter new director");
            String director = sc.nextLine();

            System.out.println("Please enter new year");
            String year = sc.nextLine();

            ps = con.prepareStatement(query);

            ps.setString(4, title);
            ps.setString(1, title1);
            ps.setString(2, director);
            ps.setString(3, year);

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }

    }

    @Override
    public void deleteMovie() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie m = null;
        List<Movie> movies = new ArrayList<>();

        try {
            con = this.getConnection();
            String query = "delete from movies where title=?";
            Scanner sc = new Scanner(System.in);

            System.out.println("Please enter the title of the movie you want to delete ");
            String title = sc.nextLine();

            ps = con.prepareStatement(query);

            ps.setString(1, title);

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException("findAllMovies() " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }

    }

}
