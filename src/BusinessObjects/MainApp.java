// author: Jason Neary & Aidan Vaughan
package BusinessObjects;


import DTOs.Movie;
import DAOs.MySqlMovieDao;
import DAOs.MovieDaoInterface;
import Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
       
        MovieDaoInterface IMovieDao = new MySqlMovieDao();

        try {
            Scanner s = new Scanner(System.in);

            List<Movie> movies = IMovieDao.findAllMovies();

            String jsonstr = "{\"movies\":[";
            int movieCount = 0;
            
            System.out.println("What is the number of the action you want to take");
            System.out.println("findAllMovies (1)");
            System.out.println("findMovieById (2)");
            System.out.println("findMovieByTitle (3)");
            System.out.println("findMovieByDirector(4)");
            System.out.println("addMovie (5)");
            System.out.println("deleteMovie (6)");
            System.out.println("updateMovie (7)");
            
            int choice = s.nextInt();
            switch (choice) {
                //listing all movies
                case 1:
                    if( movies.isEmpty() )
                        System.out.println("There are no Movies");
                    for( Movie movie : movies )
                        System.out.println("Movie: " + movie.toString());
                    break;
                    //getting movie by an id
                case 2:
                    System.out.println("Please enter an ID");
                    int id = s.nextInt();
                    Movie movie = IMovieDao.findMovieById(id);
                    if(movie != null)
                        System.out.println("Movie found: " + movie);
                    else
                        System.out.println("Movie with that Id was not found");
                    break;
                    //getting a movie by its title
                case 3:
                    System.out.println("Please enter a title");
                     Scanner sc= new Scanner(System.in);
                    String title = sc.nextLine();
                    Movie mo = IMovieDao.findMovieByTitle(title);
                    if (mo != null) {
                        System.out.println("Movie found: " + mo);
                    } else {
                        System.out.println("Movie with that Title was not found");
                    }       break;
                    //getting a movie(s) by its director
                case 4:
                    System.out.println("Please enter a Director");
                    Scanner sd= new Scanner(System.in);
                    String director = sd.nextLine();
                    List<Movie> mov = IMovieDao.findMovieByDirector(director);
                    if (mov != null) {
                        for (int i = 0; i < mov.size(); i++) {
                            System.out.println(mov.get(i));
                        }
                    } else {
                        System.out.println("Movie with that Director was not found");
                    }       break;
                    //inserting a movie
                case 5:
                    IMovieDao.addMovies();
                    break;
                    //deleting a movie
                case 6:
                    IMovieDao.deleteMovie();
                    break;
                    //updating a movie
                default:
                    IMovieDao.updateMovie();
                    break;
            }
            
            System.out.println();
            System.out.println("Movie List converted into json format");
            
            //converting to json
            for (Movie m : movies) {

                if (movieCount > 0 && movieCount < movies.size()) {
                    jsonstr += ",";
                }

                movieCount++;

                jsonstr += "{\"Id\":\"" + m.getId() + "\","
                        + "\"Title\":\"" + m.getTitle() + "\","
                        + "\"Genre\":\"" + m.getGenre() + "\","
                        + "\"Director\":\"" + m.getDirector() + "\","
                        + "\"Runtime\":\"" + m.getRuntime() + "\","
                        + "\"Plot\":\"" + m.getPlot() + "\","
                        + "\"Location\":\"" + m.getLocation() + "\","
                        + "\"Poster\":\"" + m.getPoster() + "\","
                        + "\"Rating\":\"" + m.getRating() + "\","
                        + "\"Format\":\"" + m.getFormat() + "\","
                        + "\"Year\":\"" + m.getYear() + "\","
                        + "\"Starring\":\"" + m.getStarring() + "\","
                        + "\"Copies\":\"" + m.getCopies() + "\","
                        + "\"Barcode\":\"" + m.getBarcode() + "\","
                        + "\"User_Rating\":\"" + m.getUser_rating() + "\"}";

            }

            jsonstr += "] }";
            System.out.println(jsonstr);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private static void elseif(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
