/**
 * ---------------------------------------------------------------
 * Assignment 2
 * Movie Class
 * Written by: Sopheaktra Lean - 40225014
 * Due Date: March 27th 2024
 * COMP 249 - Section U - Winter 2024
 * ---------------------------------------------------------------*/

/** Movie class implements Serializable and includes ten instance variables which specifies in movie record*/

/** Importing the Serializable package*/
import java.io.Serializable;

/** Creating a class called "Movie" with serializable implemented*/
public class Movie implements Serializable {

    /** Declaring an integer called "year*/
    private int year;
    /** Declaring a String called "title"*/
    private String title;
    /** Declaring an integer called "duration"*/
    private int duration;
    /** Declaring a String called "genre"*/
    private String genre;
    /** Declaring a String called "rating"*/
    private String rating;
    /** Declaring a double called "score"*/
    private double score;
    /** Declaring a String called "director"*/
    private String director;
    /** Declaring a String called "actor1"*/
    private String actor1;
    /** Declaring a String called "actor2"*/
    private String actor2;
    /** Declaring a String called "actor3"*/
    private String actor3;

    /**
     * Implementing a parametrized constructor for a Movie
     *
     * @param   year          the year of the movie
     * @param   title         the title of the movie
     * @param   duration      the duration of the movie
     * @param   genre         the genre of the movie
     * @param   rating        the rating of the movie
     * @param   score         the score of the movie
     * @param   director      the director of the movie
     * @param   actor1        the first actor of the movie
     * @param   actor2        the second actor of the movie
     * @param   actor3        the third actor of the movie
     * */
    public Movie(int year, String title, int duration, String genre, String rating,
                 double score, String director, String actor1, String actor2, String actor3){
        this.year = year;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
        this.score = score;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    /**
     * Implementing an accessor for the instance variable "year"
     * @return  year
     * */
    public int getYear() {
        return year;
    }
    /**
     * Implementing a mutator for the instance variable "year"
     * @param   year          the year of the movie
     * */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Implementing an accessor for the instance variable "title"
     * @return  title
     * */
    public String getTitle() {
        return title;
    }
    /**
     * Implementing a mutator for the instance variable "title"
     * @param   title       the title of the movie
     * */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Implementing an accessor for the instance variable "duration"
     * @return  duration
     * */
    public int getDuration() {
        return duration;
    }
    /**
     * Implementing a mutator for the instance variable "duration"
     * @param   duration       the duration of the movie
     * */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Implementing an accessor for the instance variable "genre"
     * @return  genre
     * */
    public String getGenre() {
        return genre;
    }
    /**
     * Implementing a mutator for the instance variable "genre"
     * @param   genre       the genre of the movie
     * */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Implementing an accessor for the instance variable "rating"
     * @return  rating
     * */
    public String getRating() {
        return rating;
    }
    /**
     * Implementing a mutator for the instance variable "rating"
     * @param   rating       the rating of the movie
     * */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Implementing an accessor for the instance variable "score"
     * @return  score
     * */
    public double getScore() {
        return score;
    }
    /**
     * Implementing a mutator for the instance variable "score"
     * @param   score       the score of the movie
     * */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Implementing an accessor for the instance variable "director"
     * @return  director
     * */
    public String getDirector() {
        return director;
    }
    /**
     * Implementing a mutator for the instance variable "director"
     * @param   director       the director of the movie
     * */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Implementing an accessor for the instance variable "actor1"
     * @return  actor1
     * */
    public String getActor1() {
        return actor1;
    }
    /**
     * Implementing a mutator for the instance variable "actor1"
     * @param   actor1       the first actor of the movie
     * */
    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    /**
     * Implementing an accessor for the instance variable "actor2"
     * @return  actor2
     * */
    public String getActor2() {
        return actor2;
    }
    /**
     * Implementing a mutator for the instance variable "actor2"
     * @param   actor2       the second actor of the movie
     * */
    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    /**
     * Implementing an accessor for the instance variable "actor3"
     * @return  actor3
     * */
    public String getActor3() {
        return actor3;
    }
    /**
     * Implementing an accessor for the instance variable "actor3"
     * @param   actor3       the third actor of the movie
     * */
    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    /**
     * Implementing an equals() method
     *
     * @param   otherObj    the object that will be compared to
     * @return              true if two objects are equal and false otherwise
     * */
    public boolean equals(Object otherObj){
        if(otherObj == null){
            return false;
        }
        if(otherObj.getClass() != this.getClass()){
            return false;
        }
        Movie otherMovie = (Movie) otherObj;
        return  (this.year == otherMovie.year) && (this.duration == otherMovie.duration) && (this.genre.equals(otherMovie.genre)) &&
                (this.rating.equals(otherMovie.rating)) && (this.director.equals(otherMovie.director)) && (this.score == otherMovie.score) &&
                (this.actor1.equals(otherMovie.actor1)) &&(this.actor2.equals(otherMovie.actor2)) && (this.actor3.equals(otherMovie.actor3)) &&
                (this.title.equals(otherMovie.title));
    }

    /**
     * Implementing a toString() method
     *
     * @return          string that consists of the information of a Movie record with all the 10 instance variables
     * */
    public String toString() {
        return  this.year + "," +
                this.title + "," +
                this.duration + "," +
                this.genre + "," +
                this.rating + "," +
                this.score + "," +
                this.director + "," +
                this.actor1 + "," +
                this.actor2 + "," +
                this.actor3;
    }
}


