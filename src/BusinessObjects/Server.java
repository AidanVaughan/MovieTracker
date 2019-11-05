/**
 * SERVER                                                   February 2019 DL 08/03/19
 *
 * Server accepts client connections, creates a ClientHandler to handle the
 * Client communication, creates a socket and passes the socket to the handler,
 * runs the handler in a separate Thread.
 *
 *
 * The handler reads requests from clients, and sends replies to clients, all in
 * accordance with the rules of the protocol. as specified in
 * "ClientServerBasic" sample program
 *
 * The following PROTOCOL is implemented:
 *
 * If ( the Server receives the request "Time", from a Client ) then : the
 * server will send back the current time
 *
 * If ( the Server receives the request "Echo message", from a Client ) then :
 * the server will send back the message
 *
 * If ( the Server receives the request it does not recognize ) then : the
 * server will send back the message "Sorry, I don't understand"
 *
 * This is an example of a simple protocol, where the server's response is based
 * on the client's request.
 *
 *
 */
package BusinessObjects;

import DAOs.MovieDaoInterface;
import DAOs.MySqlMovieDao;
import DTOs.Movie;
import Exceptions.DaoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            MovieDaoInterface IMovieDao = new MySqlMovieDao();
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true) // loop continuously to accept new client connections
            {

                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection, 
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());
                ClientHandler ch = new ClientHandler(socket, clientNumber, IMovieDao);
                Thread t = new Thread(ch); // create a new ClientHandler for the client,
                t.start();

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");

            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable // each ClientHandler communicates with one Client
    {

        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        MovieDaoInterface dao; // reference to DAO

        public ClientHandler(Socket clientSocket, int clientNumber, MovieDaoInterface IMovieDao) {
            try {
                this.dao = IMovieDao;  // keep the reference

                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing 

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = socketReader.readLine()) != null) {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                    if (message.startsWith("findMovieById")) {
                        String[] tokens = message.split(" ");
                        int movid = Integer.parseInt(tokens[1]);
                        Movie movie = dao.findMovieById(movid);

                        String jsonString = convertToJson(movie);  // local method

                        socketWriter.println(jsonString);
                    } else if (message.startsWith("findMovieByTitle")) {
                        String input = message.substring(17);
                        String title = input;
                        Movie movie = dao.findMovieByTitle(title);
                        String json = convertToJson(movie);// strip off the 'Echo ' part
                        socketWriter.println(json);  // send message to client
                    }else if (message.startsWith("findAllMovies")) {
                        List<Movie> movies = dao.findAllMovies();
                        String json = convertToJsonList(movies);// strip off the 'Echo ' part
                        socketWriter.println(json);  // send message to client
                    }
                    else if (message.startsWith("findMovieByDirector")) {
                        String input = message.substring(20);
                        List<Movie> movies = dao.findMovieByDirector(input);
                        String json = convertToJsonList(movies);// strip off the 'Echo ' part
                        socketWriter.println(json);  // send message to client
                    }
                    else {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                   
                }
                 socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DaoException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

//                
//            } catch (IOException ex)
//            {
//                ex.printStackTrace();
//            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }

        public String convertToJsonList(List<Movie> m) throws DaoException {
            String jsonstr = "{\"movies\":[";
            int movieCount = 0;

            for (Movie mo : m) {

                if (movieCount > 0 && movieCount < m.size()) {
                    jsonstr += ",";
                }

                movieCount++;

                jsonstr += "{\"Id\":\"" + mo.getId() + "\","
                        + "\"Title\":\"" + mo.getTitle() + "\","
                        + "\"Genre\":\"" + mo.getGenre() + "\","
                        + "\"Director\":\"" + mo.getDirector() + "\","
                        + "\"Runtime\":\"" + mo.getRuntime() + "\","
                        + "\"Plot\":\"" + mo.getPlot() + "\","
                        + "\"Location\":\"" + mo.getLocation() + "\","
                        + "\"Poster\":\"" + mo.getPoster() + "\","
                        + "\"Rating\":\"" + mo.getRating() + "\","
                        + "\"Format\":\"" + mo.getFormat() + "\","
                        + "\"Year\":\"" + mo.getYear() + "\","
                        + "\"Starring\":\"" + mo.getStarring() + "\","
                        + "\"Copies\":\"" + mo.getCopies() + "\","
                        + "\"Barcode\":\"" + mo.getBarcode() + "\","
                        + "\"User_Rating\":\"" + mo.getUser_rating() + "\"}";

            }
            jsonstr += "] }";
            return jsonstr;
        }
    }

    public String convertToJson(Movie m) throws DaoException {
        String jsonstr = "{\"movies\":"; //{\"movies\":[";

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

        jsonstr += " }";
        return jsonstr;
    }
}
