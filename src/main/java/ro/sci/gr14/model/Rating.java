
/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

package ro.sci.gr14.model;

/**
 * POJO class used to manage input from customers about a handyman rating
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
public class Rating {

    private int votes;
    private double averageStars;

    /**
     * @param votes
     * @param averageStars
     */
    public Rating(int votes, double averageStars){
        this.votes = votes;
        this.averageStars = averageStars;
    }

    /**
     * @return votes as int
     */
    public int getVotes( ){
        return votes;
    }

    /**
     * @param votes the number of votes to set
     */
    public void setVotes(int votes){
        this.votes = votes;
    }

    /**
     * @return averageStars as double
     */
    public double getAverageStars( ){
        return averageStars;
    }

    /**
     * @param averageStars the average number of votes to set
     */
    public void setAverageStars(double averageStars){
        this.averageStars = averageStars;
    }
}

