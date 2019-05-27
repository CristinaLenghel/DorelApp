package ro.sci.gr14.model;

public class Rating {

    private int votes;
    private int averageStars;

    public Rating(int votes, int averageStars) {
        this.votes = votes;
        this.averageStars = averageStars;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(int averageStars) {
        this.averageStars = averageStars;
    }
}
