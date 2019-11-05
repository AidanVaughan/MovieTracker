/** CLIENT                                                  February 2019 DL 08/03/19
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */
package BusinessObjects;

import DAOs.MovieDaoInterface;
import DAOs.MySqlMovieDao;
import DTOs.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {
        MovieDaoInterface IMovieDao = new MySqlMovieDao();
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket, and open new socket

            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort());

            System.out.println("Client: This Client is running and has connected to the server");

            System.out.println("[Commands: \"Time\" to get time, or \"Echo message\" to get echo)]");
            System.out.println("Please enter a command: ");

            String command = in.nextLine();  // read a command from the user

            OutputStream os = socket.getOutputStream();

            PrintWriter socketWriter = new PrintWriter(os, true);// true=> auto flush buffers
            socketWriter.println(command);  // write command to socket
            InputStream inputStream = socket.getInputStream();
            Scanner socketReader = new Scanner(socket.getInputStream());

            if (command.startsWith("findMovieById")) // we expect the server to return a time (in milliseconds)
            {

                // receive json string
//                JsonReader reader = Json.createReader(inputStream);
//                JsonObject object = reader.readObject();
                String response = socketReader.nextLine();

                System.out.println(response);

                JsonReader reader = Json.createReader(new StringReader(response));
                JsonObject jsobject = reader.readObject();
                JsonObject object = jsobject.getJsonObject("movies");
                String Id = object.getString("Id");
                int id = Integer.parseInt(Id);

                String title = object.getString("Title");
                String genre = object.getString("Genre");
                String director = object.getString("Director");
                String runtime = object.getString("Runtime");
                String plot = object.getString("Plot");
                String location = object.getString("Location");
                String poster = object.getString("Poster");
                String rating = object.getString("Rating");
                String format = object.getString("Format");
                String year = object.getString("Year");
                String starring = object.getString("Starring");
                String Copies = object.getString("Copies");
                int copies = Integer.parseInt(Copies);
                String barcode = object.getString("Barcode");
                String userrating = object.getString("User_Rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userrating);

                System.out.println("Client: Response from server: Movie: " + m.toString());
            } else if (command.startsWith("findMovieByTitle")) {
                String response = socketReader.nextLine();

                System.out.println(response);

                JsonReader reader = Json.createReader(new StringReader(response));
                JsonObject jsobject = reader.readObject();
                JsonObject object = jsobject.getJsonObject("movies");
                String Id = object.getString("Id");
                int id = Integer.parseInt(Id);

                String title = object.getString("Title");
                String genre = object.getString("Genre");
                String director = object.getString("Director");
                String runtime = object.getString("Runtime");
                String plot = object.getString("Plot");
                String location = object.getString("Location");
                String poster = object.getString("Poster");
                String rating = object.getString("Rating");
                String format = object.getString("Format");
                String year = object.getString("Year");
                String starring = object.getString("Starring");
                String Copies = object.getString("Copies");
                int copies = Integer.parseInt(Copies);
                String barcode = object.getString("Barcode");
                String userrating = object.getString("User_Rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userrating);

                System.out.println("Client: Response from server: Movie: " + m.toString());

            }else if (command.startsWith("findAllMovies")) {
                String response = socketReader.nextLine();

                System.out.println(response);

                JsonReader reader = Json.createReader(new StringReader(response));
                JsonObject jsobject = reader.readObject();
                JsonArray movieobject = jsobject.getJsonArray("movies");
                List<Movie> movies = new ArrayList<>();
                
                for(int i = 0; i<movieobject.size();i++){
                     JsonObject object = movieobject.getJsonObject(i);
                String Id = object.getString("Id");
                int id = Integer.parseInt(Id);

                String title = object.getString("Title");
                String genre = object.getString("Genre");
                String director = object.getString("Director");
                String runtime = object.getString("Runtime");
                String plot = object.getString("Plot");
                String location = object.getString("Location");
                String poster = object.getString("Poster");
                String rating = object.getString("Rating");
                String format = object.getString("Format");
                String year = object.getString("Year");
                String starring = object.getString("Starring");
                String Copies = object.getString("Copies");
                int copies = Integer.parseInt(Copies);
                String barcode = object.getString("Barcode");
                String userrating = object.getString("User_Rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userrating);
                movies.add(m);
                   
                }
                for(Movie mo:movies){
                    System.out.println(mo.toString());
                }

            }
            else if (command.startsWith("findMovieByDirector")) {
                String response = socketReader.nextLine();

                System.out.println(response);

                JsonReader reader = Json.createReader(new StringReader(response));
                JsonObject jsobject = reader.readObject();
                JsonArray movieobject = jsobject.getJsonArray("movies");
                List<Movie> movies = new ArrayList<>();
                
                for(int i = 0; i<movieobject.size();i++){
                     JsonObject object = movieobject.getJsonObject(i);
                String Id = object.getString("Id");
                int id = Integer.parseInt(Id);

                String title = object.getString("Title");
                String genre = object.getString("Genre");
                String director = object.getString("Director");
                String runtime = object.getString("Runtime");
                String plot = object.getString("Plot");
                String location = object.getString("Location");
                String poster = object.getString("Poster");
                String rating = object.getString("Rating");
                String format = object.getString("Format");
                String year = object.getString("Year");
                String starring = object.getString("Starring");
                String Copies = object.getString("Copies");
                int copies = Integer.parseInt(Copies);
                String barcode = object.getString("Barcode");
                String userrating = object.getString("User_Rating");
                Movie m = new Movie(id, title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userrating);
                movies.add(m);
                   
                }
                for(Movie mo:movies){
                    System.out.println(mo.toString());
                }

            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }
}
