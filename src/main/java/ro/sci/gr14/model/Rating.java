package ro.sci.gr14.model;

public class Rating {

    private int votes;
    private double averageStars;

    public Rating(int votes, double averageStars) {
        this.votes = votes;
        this.averageStars = averageStars;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(double averageStars) {
        this.averageStars = averageStars;
    }
}
