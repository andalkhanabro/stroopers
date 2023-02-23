package model;

// the Score of the user with a point count, and the name of the user

public class Score {

    private int points; // the points scored by the user
    private String userName; // the name of the user who just played the game

    // EFFECTS: creates a score with 0 default points when game begins
    public Score() {
        this.points = 0;

    }

    // EFFECTS: Creates a score instance with the given name and points
    public Score(String name, int points) {
        this.userName = name;
        this.points = points;
    }


    // EFFECTS: Converts each score entry to a displayable string format
    @Override
    public String toString() {

        int currentPoints = this.points;

        String userName = this.userName;

        return "Name: " + userName + " | " + "Points: " + currentPoints;
    }

    public int getPoints() {

        return this.points;
    }

    public String getName() {

        return this.userName;
    }

    // EFFECTS: Increments point count by 1 on each correct user answer
    public void updatePointCount() {

        this.points += 1;
    }

}
