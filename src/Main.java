/**
 * ---------------------------------------------------------------
 * Assignment 2
 * Main Class (Part 1, Part 2, Part 3, and User-interaction)
 * Written by: Sopheaktra Lean - 40225014
 * Due Date: March 27th 2024
 * COMP 249 - Section U - Winter 2024
 * --------------------------------------------------------------- */

/* Importing the packages such as java.io, java.io.FileReader, java.nio.charset.StandardCharsets, java.nio.file.Path,
java.util.Scanner, java.nio.file.Files, and java.util.stream.Stream to utilize in the class*/
import java.io.*;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.stream.Stream;

/*Implementing a class called "Main" to run the program*/
public class Main {

    public static void main(String[] args) {

        /*Initializing a scanner called "scanner" to store and read the user's input*/
        Scanner scanner = new Scanner(System.in);

        /*Displaying a welcome message to the user*/
        String welcomeMsg = """
                            *******************************************
                            *     Welcome to Sopheaktra's Program!    *
                            *******************************************""";
        System.out.println(welcomeMsg);

        /*Initializing a string called "part1_manifest"*/
        String part1_manifest = "part1_manifest.txt";
        /*Calling the method creatingManifestFiles()*/
        creatingManifestFiles(part1_manifest);
        /*Initializing a string called "part2_manifest"*/
        String part2_manifest = do_part1(part1_manifest);
        /*Initializing a string called "part3_manifest"*/
        String part3_manifest = do_part2(part2_manifest);
        /*Calling the method creatingManifest3()*/
        creatingManifest3(part3_manifest);
        /*Calling the method "interaction" to interact with the user*/
        interacting(scanner);
    }

    /**
     * Implementing a method called "creatingManifestFiles()".
     * Creating a file called "part1_manifest.txt".
     * Containing the name of the movies file that will store the records.
     *
     * @param file          the manifest file that want to be created
     * */
    public static void creatingManifestFiles(String file){
        try{
            /*Initializing a fileWriter to write a text file */
            FileWriter fw = new FileWriter(file);
            String fileMovies;
            /*Looping from the year 1990 to 1999 to write the string of the movie file*/
            for(int i = 1990; i < 2000; i++){
                fileMovies = "Movies" + i + ".csv" + "\n";
                fw.write(fileMovies);
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * PART 1: Partition Movies to Create Input Files for Part 2.
     * Implementing a method called "do_part1".
     * Reading the all the movie records from all the files.
     * Validate each movie record using exceptions.
     * Partition valid movie records to new genre-based text files.
     * Partition bad movie records to a bad movie text file.
     *
     * @param fileName      the name of file that will be validated to
     * @return              a string called "part2_manifest"
     * */
    public static String do_part1(String fileName){

        /*Initializing an array of string called "genres" to store the 17 type of genre*/
        String[] genres = {
                "musical", "comedy", "animation", "adventure", "drama", "crime", "biography",
                "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family", "romance", "thriller",
                "western"
        };

        /*Declaring a BufferedReader called "reader" and "reader2"*/
        BufferedReader reader;
        BufferedReader reader2;

        /*Implementing a try-catch*/
        try {
            /*Initializing a file for the bad_movie_records.txt*/
            File file = new File("bad_movie_record.txt");
            file.delete();

            /*Initializing a file for the part2_manifest.txt*/
            File part2 = new File("part2_manifest.txt");
            part2.delete();

            /*Implementing a try-catch*/
            try {
                /*Initializing a FileWriter called "fw" to write a text file called "part2_manifest.txt"*/
                FileWriter fw = getFileWriter(genres);
                fw.close();
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }

            /*Reading "part1_manifest.txt"*/
            reader = new BufferedReader(new FileReader(fileName));
            /*Storing each line of part1_manifest.txt as a movieFile*/
            String movieFile = reader.readLine();

            /*Implementing this operation if the movieFile is not null*/
            while (movieFile != null) {

                /*Implementing a try-catch*/
                try {
                    /*Reading each movie file*/
                    reader2 = new BufferedReader(new FileReader(movieFile));
                    /*Initializing an integer called "line" to count the number of line in the movie file*/
                    int line = 0;
                    /*Storing each line of movieFile as a movieRecord*/
                    String movieRecord = reader2.readLine();

                    /*Implementing this operation if the movieRecord is not null*/
                    while (movieRecord != null) {
                        /*Validating the movieRecord*/
                        validate(movieRecord, genres, movieFile, line);
                        /*Reading the next movieRecord*/
                        movieRecord = reader2.readLine();
                        /*Incrementing line by 1*/
                        line++;
                    }

                    /*Closing reader2*/
                    reader2.close();
                }
                catch (Exception e) {
                    /*Displaying an exception message if an exception is caught*/
                    System.out.println(e.getMessage());
                }

                /*Reading the next movieFile*/
                movieFile = reader.readLine();
            }
            /*Closing reader*/
            reader.close();
        }
        catch (Exception e) {
            /*Displaying an exception message if an exception is caught*/
            System.out.println(e.getMessage());
        }

        /*Returning a string called "part2_manifest.txt"*/
        return "part2_manifest.txt";
    }/*End of do_part1() method*/

    /**
     * Implementing a static method called "getFileWriter"
     * Writing the part2_manifest.txt with the genre-based text files
     *
     * @param genres        the array of genres that will be used to generate text file
     * @return              the fileWriter
     * */
    public static FileWriter getFileWriter(String[] genres) throws IOException {
        FileWriter fw = new FileWriter("part2_manifest.txt", true);
        /*Using enhanced loop to go through each genre in genres to create genre-based text file*/
        for(String genre : genres) {
            String genreFileName = genre + ".csv";
            File genreFile = new File(genreFileName);
            genreFile.delete();
            genreFile.createNewFile();
            fw.write(genreFileName+ "\n");
        }
        return fw;
    }/*End of getFileWriter() method*/


    /**
     * Implementing a void method called "validate()"
     * Checking the syntax and semantic error of each movie record if exist
     *
     * @param genres        the array of genres
     * @param record        the movie record that will be validated
     * @param movieFile     the file that the movie record comes from
     * @param numLine       the position of the movie record in the movie file
     **/
    public static void validate(String record, String[] genres, String movieFile, int numLine){
        /*Implementing a try-catch block*/
        try {
            /*Initializing an array of string called "tokens" which will split the movie record*/
            String[] tokens = record.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            /*Iterating through tokens to trim all the extra white space around each string*/
            for(int i = 0; i < tokens.length; i++){
                /*Trimming each string*/
                tokens[i] = tokens[i].trim();
            }

            /*Implementing enhanced loop through tokens*/
            for (String str : tokens) {
                /*Initializing an integer called "comma" which will find the index of the first comma*/
                int comma = str.indexOf(',');
                /*Initializing an integer called "startQ" which will find the index of the first quotation*/
                int startQ = str.indexOf('"');

                /*Implementing this operation if comma is not -1, startQ is not -1 and comma is less than startQ*/
                if (comma != -1 && startQ != -1 && comma < startQ) {
                    /*The MissingQuoteException will be thrown*/
                    throw new MissingQuoteException("missing quote");
                }
            }

            /*Initializing an array of strings called "errorNames"*/
            String[] errorNames = {"year", "title", "duration", "genre", "rating", "score", "name(s)", "name(s)", "name(s)", "name(s)"};

            /*Implementing this operation if tokens' length is less than 10*/
            if (tokens.length < 10) {
                /*The MissingFieldException will be thrown*/
                throw new MissingFieldsException("missing field(s)");
            }
            /*Implementing this operation if tokens' length is greater than 10*/
            else if (tokens.length > 10) {
                /*The ExcessFieldException will be thrown*/
                throw new ExcessFieldsException("excess field(s)");
            }

            /*Implementing this operation if tokens' length equals 10 or the number of data fields are 10
            * This operation means that the record has passed the syntax error
            * The record is going to be checked for the semantic error
            * If the record passes the semantic error, it will be added to genre-based text file*/
            else {
                /*Iterating through each element of tokens*/
                for (int i = 0; i < tokens.length; i++) {
                    /*Initializing each tokens at index i to a string called "data"*/
                    String data = tokens[i];

                    /*Implementing this operation if data is empty*/
                    if (data.isEmpty()) {
                        /*The MissingDataException will be thrown*/
                        throw new MissingDataException("missing " + errorNames[i]);
                    }

                    /*Implementing this operation if index i = 0*/
                    if (i == 0) {
                        /*Implementing a try-catch*/
                        try {
                            /*Initializing data to an integer called "year"*/
                            int year = Integer.parseInt(data);
                            /*Implementing this operation if year is less than 1990 or greater than 1999*/
                            if (year < 1990 || year > 1999) {
                                /*The BadYearException will be thrown*/
                                throw new BadYearException("invalid year");
                            }
                        } catch (NumberFormatException e) {
                            /*The BadYearException will be thrown*/
                            throw new BadYearException("invalid year");
                        }
                    }
                    
                    /*Implementing this operation if index i = 2*/
                    else if (i == 2) {
                        /*Implementing a try-catch*/
                        try {
                            /*Initializing data to an integer called "duration"*/
                            int duration = Integer.parseInt(data);
                            /*Implementing this operation if duration is less than 30 or greater than 300*/
                            if (duration < 30 || duration > 300) {
                                /*The BadDurationException will be thrown*/
                                throw new BadDurationException("invalid duration");
                            }
                        } catch (NumberFormatException e) {
                            /*The BadDurationException will be thrown*/
                            throw new BadDurationException("invalid duration");
                        }
                    }

                    /*Implementing this operation if index i = 3*/
                    else if(i == 3){
                        /*Initializing a boolean called "found"*/
                        boolean found = false;
                        /*Implementing an enhanced loop for genres*/
                        for(String genre : genres){
                            /*Implementing this operation if data exists in genres*/
                            if(data.equalsIgnoreCase(genre)){
                                /*Assigning found to true*/
                                found = true;
                                break;
                            }
                        }
                        /*Implementing this operation if not found*/
                        if(!found){
                            /*The BadGenreException will be thrown*/
                            throw new BadGenreException("invalid genre");
                        }
                    }
                    /*Implementing this operation if index i = 4*/
                    else if(i == 4){
                        /*Initializing a boolean called "found"*/
                        boolean found = false;
                        /*Initializing an array of string called "ratings"*/
                        String[] ratings = {"PG", "Unrated", "G", "R", "PG-13", "NC-17"};
                        /*Implementing an enhanced loop for genres*/
                        for(String rating : ratings){
                            /*Implementing this operation if data exists in ratings*/
                            if(data.equalsIgnoreCase(rating)){
                                /*Assigning found to true*/
                                found = true;
                                break;
                            }
                        }
                        /*Implementing this operation if not found*/
                        if(!found){
                            /*The BadRatingException will be thrown*/
                            throw new BadRatingException("invalid rating");
                        }
                    }
                    /*Implementing this operation if index i = 5*/
                    else if(i == 5){
                        /*Implementing a try-catch*/
                        try {
                            /*Initializing data to a double called "score"*/
                            double score = Double.parseDouble(data);
                            /*Implementing this operation if score is less than 0 or greater than 10*/
                            if (score < 0 || score > 10) {
                                /*The BadScoreException will be thrown*/
                                throw new BadScoreException("invalid score");
                            }
                        } catch (NumberFormatException e) {
                            /*The BadScoreException will be thrown*/
                            throw new BadScoreException("invalid score");
                        }
                    }
                }

                /*Initializing a string called "fileName" with the genre of the movie*/
                String fileName = tokens[3].toLowerCase() + ".csv";
                /*Implementing a try-catch*/
                try {
                    /*Initializing a fileWriter called fw with fileName*/
                    FileWriter fw = new FileWriter(fileName, true);
                    /*Writing the movie record into the file*/
                    fw.write(String.join(",", tokens) + "\n");
                    fw.close();
                }catch(Exception ex){
                    /*Displaying a message if an exception occurs*/
                    System.out.println(ex.getMessage());
                }

            }
        }
        catch (Exception e) {
            /*Implementing a try-catch*/
            try {
                /*Initializing a fileWriter called fw to write the bad movie record*/
                FileWriter fw = new FileWriter("bad_movie_record.txt", true);
                /*Initializing a string called "badRecord" to store the required information of a bad record movie*/
                String badRecord = e.getMessage() + "\n" +
                        "The original movie record: "+ record + "\n" +
                        "The input movie file: " + movieFile + "\n" +
                        "The position of the record: " + numLine + "\n" + "\n";
                /*Writing the badRecord into the file*/
                fw.write(badRecord);
                fw.close();
            } catch(Exception ex){
                /*Displaying a message if an exception occurs*/
                System.out.println(ex.getMessage());
            }
        }
    }/*End of validate() method*/

    /**
     * PART 2: Create and Serialize Arrays of Movie Records.
     * Implementing a method called "do_part2".
     * Loading the records from the genre-based text files into an array of Movie objects.
     * Serialize the array into a binary file.
     *
     * @param filePath          the name of file
     * @return                  a string contains "part3_manifest"
     * */
    public static String do_part2(String filePath){

        /*Initializing a BufferedReader called "reader" and "reader2"*/
        BufferedReader reader;
        BufferedReader reader2;

        /*Implementing a try-catch*/
        try{
            /*Assigning reader to read the filepath*/
            reader = new BufferedReader(new FileReader(filePath));
            /*Storing each line of the filePath to a string called "genreFile"*/
            String genreFile = reader.readLine();

            /*Implementing this operation if genreFile is not null*/
            while(genreFile != null){
                /*Implementing a try-catch*/
                try {
                    /*Declaring an integer called lineCount*/
                    int lineCount;
                    /*Counting the number of line in a genreFile*/
                    Stream<String> stream = Files.lines(Path.of(genreFile), StandardCharsets.UTF_8 );
                    lineCount = (int) stream.count();

                    /*Initializing an array of movies called "movies" with the length of lineCount*/
                    Movie[] movies = new Movie[lineCount];
                    /*Assigning an integer called "index" to 0*/
                    int index = 0;

                    /*Assigning reader2 to read each genreFile*/
                    reader2 = new BufferedReader(new FileReader(genreFile));
                    /*Storing each line of genreFile to a string called eachGenre*/
                    String eachGenre = reader2.readLine();

                    /*Implementing this operation if eachGenre or each movie record is not null*/
                    while (eachGenre != null) {
                        /*Splitting eachGenre into an array of string called "tokens"*/
                        String[] tokens = eachGenre.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                        /*Initializing a movie called "modifiedRecord" with the parameters from tokens*/
                        Movie modifiedRecord = new Movie(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), tokens[3],
                                tokens[4], Double.parseDouble(tokens[5]), tokens[6], tokens[7], tokens[8], tokens[9]);
                        /*Assigning the movies at index to be modifiedRecord*/
                        movies[index++] = modifiedRecord;
                        eachGenre = reader2.readLine();
                    }

                    /*Serializing array of movies of each genreFile*/
                    serializeMovies(movies, genreFile);

                    reader2.close();
                }
                catch(Exception e){
                    /*Displaying an exception message if an exception occurs*/
                    System.out.println(e.getMessage());
                }
                genreFile = reader.readLine();
            }
            reader.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        /*Returning a string*/
        return "part3_manifest.txt";
    }/*End of do_part2() method*/

    /**
     * Implementing a static method called creatingManifest3().
     * Create serialized file of each genre
     * */
    public static void creatingManifest3(String file) {
        //String file = "part3_manifest.txt";
        try {
            /*Initializing a fileWriter to write a text file */
            FileWriter fw = new FileWriter(file);
            String genreFile;
            /*Looping from the index 0 to 16 to write the string of the genre file*/
            for (int i = 0; i < 17; i++) {
                genreFile = getGenreType(i) + ".ser" + "\n";
                fw.write(genreFile);
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } /*End of creatingManifest3() method*/

    /**
     * Implementing a method called "serializeMovies".
     * Serialize array of movies into a binary file.
     *
     * @param movies        the array of movies that will be serialized
     * @param filename      the name of the file that the movies are from
     * */
    public static void serializeMovies(Movie[] movies, String filename) {
        /*Initializing an integer called "dot" to find the dot in fileName*/
        int dot = filename.indexOf('.');
        /*Initializing a string called "serializedGenreFile"*/
        String serializedGenreFile = filename.substring(0, dot) + ".ser";
        /*Implementing a try-catch*/
        try {
            /*Initializing a fileOutputStream called "file"*/
            FileOutputStream file = new FileOutputStream(serializedGenreFile);
            ObjectOutputStream out = new ObjectOutputStream(file);
            /*Writing movies into binary*/
            out.writeObject(movies);
            out.close();
            file.close();
        } catch (IOException e) {
            /*Displaying an exception message if an exception occurs*/
            System.out.println("IOException is caught");
        }
    } /*End of serializedMovie() method*/

    /**
     * Implementing a method called do_part3()
     * Deserializing the binary file and store object back into an array
     *
     * @param mainSerializedFile        the file that has been serialized
     * @return                          the two dimension array
     * */
    public static Movie[][] do_part3(String mainSerializedFile){

        /*Implementing a try-catch*/
        try{
            /*Initializing a bufferedReader called "reader" to read the mainSerializedFile*/
            BufferedReader reader = new BufferedReader(new FileReader(mainSerializedFile));
            /*Initializing an integer called "numGenre" to count the number of genre in the mainSerializedFile*/
            int numGenre = 0;
            /*If the line is not null, increment numGenre*/
            while(reader.readLine() != null){
                numGenre++;
            }
            reader.close();

            /*Declaring a 2-D array while the first the array has the length of numGenre*/
            Movie[][] movies = new Movie[numGenre][];
            /*Reading the mainSerializedFile*/
            reader = new BufferedReader(new FileReader(mainSerializedFile));
            String line;
            /*Initializing an integer called "genreIndex" indicating the genre*/
            int genreIndex = 0;
            /*Implementing this operation of the genre file is not null*/
            while((line = reader.readLine()) != null){
                String genreFileName = line.trim();
                try{
                    /*Deserializing the genreFileName*/
                    FileInputStream deserializedGenreFile = new FileInputStream(genreFileName);
                    ObjectInputStream in = new ObjectInputStream(deserializedGenreFile);

                    /*Assigning the movies at genreIndex to the array of cast objects*/
                    movies[genreIndex] = (Movie[])in.readObject();
                    in.close();
                    deserializedGenreFile.close();

                }catch(IOException e){
                    /*Displaying a message if exception occurs*/
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    /*Exception will be thrown if it occurs*/
                    throw new RuntimeException(e);
                }
                /*Incrementing genreIndex because there are many types of genre*/
                genreIndex++;
            }
            reader.close();
            /*Returning the two dimension movies*/
            return movies;
        }
        catch(IOException e){
            /*Displaying a message if exception occurs*/
            System.out.println(e.getMessage());
        }
        /*Returning a null 2 dimension array if the mainSerializedFile is empty*/
        return null;
    } /*End of do_part3() method*/

    /**
     * Implementing a static method called getGenreType()
     *
     * @param index         the index of the genre in the array of genres
     * @return              the type of genre in that index
     * */
    public static String getGenreType(int index){
        String[] genres = {
                "musical", "comedy", "animation", "adventure", "drama", "crime", "biography",
                "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family",
                "romance", "thriller","western"
        };
        return genres[index];
    }/*End of getGenreType() method*/

    /**
     * Implementing a static method called "mainMenu()"
     *
     * @return              the string of main menu that will be used to interact with the user*/
    public static String mainMenu(int index){
        int[] numMovies = numMovies();
        return String.format("""
               --------------------------------------
                            Main Menu                   \s
               --------------------------------------
               s    Select a movie array to navigate    \s
               n    Navigate %s movies (%d records)     \s
               x    Exit                                \s
               --------------------------------------   \s
               Enter your choice:""", getGenreType(index), numMovies[index]);
    }/*End of mainMenu() method*/

    /**
     * Implementing a method called "numMovies()"
     * Iterating through the 2 dimension array to get the number of movies of each genre after deserialization
     *
     * @return          an integer array that contains the number of movies
     * */
    public static int[] numMovies(){
        /*Initializing a 2 dimension array called movies*/
        Movie[][] movies = do_part3("part3_manifest.txt");
        /*Initializing an array of integers that contain the number of movies of each genre*/
        int[] numMovies = new int[0];
        if (movies != null) {
            numMovies = new int[movies.length];
        }
        if (movies != null) {
            /*Iterating through movies (through each genre)*/
            for(int i = 0; i < movies.length; i++){
                /*Iterating through genres (through each movie)*/
                for(int j = 0; j < movies[i].length; j++){
                    if(movies[i][j] != null){
                        /*Incrementing the numMovies of each genre*/
                        numMovies[i]++;
                    }
                }
            }
        }
        return numMovies;
    }/*End of numMovies() method*/

    /**
     * Implementing a method called "genreMenu()"
     *
     * @return          the string of genre menu
     * */
    public static String genreMenu(){
        int[] numMovie= numMovies();
        return String.format(
                """
                        -------------------------------------------
                                       Genre Sub-Menu             \s
                        -------------------------------------------
                        0   musical                     (%d movies)
                        1   comedy                      (%d movies)
                        2   animation                   (%d movies)
                        3   adventure                   (%d movies)
                        4   drama                       (%d movies)
                        5   crime                       (%d movies)
                        6   biography                   (%d movies)
                        7   horror                      (%d movies)
                        8   action                      (%d movies)
                        9   documentary                 (%d movies)
                        10  fantasy                     (%d movies)
                        11  mystery                     (%d movies)
                        12  sci-fi                      (%d movies)
                        13  family                      (%d movies)
                        14  western                     (%d movies)
                        15  romance                     (%d movies)
                        16  thriller                    (%d movies)
                        17  exit                                  \s
                        -------------------------------------------
                        Enter your choice:""",numMovie[0], numMovie[1],
                numMovie[2],numMovie[3],numMovie[4],numMovie[5],
                numMovie[6],numMovie[7],numMovie[8],numMovie[9],
                numMovie[10],numMovie[11],numMovie[12],numMovie[13],
                numMovie[14],numMovie[15],numMovie[16]);
    }/*End of genreMenu() method*/

    /**
     * Implementing a method called navigate()
     * User will be able to navigate through the array of movies they wish to see
     *
     * @param index         the index of genre
     * @param scanner       storing and reading the user's input
     * */
    public static void navigate(Scanner scanner, int index){
        /*Initializing a 2D array called "movies"*/
        Movie[][]  movies = do_part3("part3_manifest.txt");
        /*Initializing an array of integer to store the number of movie of each genre*/
        int[] numMovie = numMovies();
        int numRecords = numMovie[index];
        /*Initializing an integer called "navigatingIndex"*/
        int navigatingIndex = -1;
        /*Initializing an integer called "startIndex"*/
        int startIndex = 0;
        /*Declaring an integer called "endIndex"*/
        int endIndex;

        /*Implementing this operation if the number of movie equals 0*/
        if(numMovie[index] == 0){
            /*Displaying a message to the user if there is no record in a genre*/
            System.out.println("There is zero record in the this genre. Navigation failed.");
        }
        /*Implementing this operation if the number of movie is not 0*/
        else {
            /*Looping for navigating through the movies until the user enters 0*/
            while (navigatingIndex != 0) {
                /*Displaying the navigation menu*/
                String navigation = String.format(
                        "Navigating %s movies (%d record(s))" + "\n" +
                                "Enter your choice: "
                        , getGenreType(index), numRecords);
                System.out.println(navigation);

                /*Initializing an integer scanner called "navigatingIndex" to store and read the user's input*/
                navigatingIndex = scanner.nextInt();

                /*Implementing this operation if navigatingIndex < 0*/
                if (navigatingIndex < 0) {
                    endIndex = startIndex + navigatingIndex;

                    if(endIndex < 0){
                        System.out.println("BOF has been reached");
                        endIndex = 0;
                        for(int i = startIndex; i>= endIndex; i--){
                            if (movies != null) {
                                System.out.println("The position of the movie: " + i + " " + movies[index][i]);
                            }
                            startIndex = i;
                        }
                    }
                    else if(endIndex < startIndex){
                        endIndex++;
                        for(int i = startIndex; i >= endIndex; i--){
                            if (movies != null) {
                                System.out.println("The position of the movie: " + i + " " + movies[index][i]);
                            }
                            startIndex = i;
                        }
                    }
                }

                /*Implementing this operation if navigatingIndex > 0*/
                else if (navigatingIndex > 0) {
                    endIndex = startIndex + navigatingIndex;

                    if(endIndex > numMovie[index]){
                        System.out.println("EOF has been reached.");
                        endIndex = numMovie[index];
                        for(int i = startIndex; i < endIndex; i++){
                            if (movies != null) {
                                System.out.println("The position of the movie: " + i + " " + movies[index][i]);
                            }
                        }
                        startIndex = endIndex - 1;
                    }
                    else{
                        for(int i = startIndex; i < endIndex; i++){
                            if (movies != null) {
                                System.out.println("The position of the movie: " + i + " " + movies[index][i]);
                            }
                        }
                        startIndex = endIndex - 1;
                    }
                }
            }
        }
    }/*End of navigating() method*/

    /**
     * Implementing a method called "interacting()".
     * User will be able to navigate the movies from all the files.
     *
     * @param scanner           to store and read the user's input
     * */
    public static void interacting(Scanner scanner){
        /*Initializing an integer called "index"*/
        int index = 0;
        /*Initializing a boolean called "valid"*/
        boolean valid = true;

        /*Implementing this operation as long as the valid is true*/
        while(valid){
            /*Displaying the main menu*/
            System.out.println(mainMenu(index));
            /*Initializing a string called "choice" to store and read the user's input*/
            String choice = scanner.next();
            switch(choice) {
                /*Implementing this case if choice is s*/
                case "s":
                    /*Displaying the genreMenu()*/
                    System.out.println(genreMenu());
                    /*Assigning index depends on the user's input*/
                    index = scanner.nextInt();
                    /*Implementing this operation if the user enters 17*/
                    if(index == 17){
                        System.out.println("You have exit the system.");
                        /*Assigning valid to false*/
                        valid = false;
                    }
                    break;
                /*Implementing this case if choice is n*/
                case "n":
                    /*Implementing the navigating method*/
                    navigate(scanner, index);
                    break;
                /*Implementing this case if choice is x*/
                case "x":
                    /*Displaying a message to the user*/
                    System.out.println("You have exit the system. Thank you for using our system.");
                    /*Assigning valid to false*/
                    valid = false;
                    break;
                /*Implementing this case if choice is anything else other than s, n, and x*/
                default:
                    System.out.println("You have entered an invalid choice. Please try again");
            }
        }
    }/*End of interacting() method*/

} /*End of Main class*/