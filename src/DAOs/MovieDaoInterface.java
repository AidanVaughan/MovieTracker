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
import java.util.List;

public interface MovieDaoInterface {
    
    public List<Movie> findAllMovies() throws DaoException;
    public Movie findMovieById(int mId) throws DaoException;
    public Movie findMovieByTitle(String mtitle)throws DaoException;
    public List<Movie> findMovieByDirector(String mdirector)throws DaoException;
    public void addMovies() throws DaoException;
    public void deleteMovie() throws DaoException;
    public void updateMovie() throws DaoException;
}
