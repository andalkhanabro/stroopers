package model;

// the Score of the user with a point count, and the name of the user for each Stroop Game

import org.json.JSONObject;
import persistence.Writable;

public class Score implements Writable {
    private int points; // the points scored by the user in that attempt
    private String userName; // the name of the user who just played the game

    // EFFECTS: creates a score with 0 default points when game begins
    public Score() {
        this.points = 0;

    }

    // REQUIRES: name cannot be null or an empty string, points should be >= 0
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

    // MODIFIES: this
    // EFFECTS: Increments point count by 1 on each correct user answer
    public void updatePointCount() {

        this.points += 1;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        Integer pointCount = this.points;
        String pointsJsonFormat = pointCount.toString();
        json.put("name", userName);
        json.put("points", pointsJsonFormat);
        return json;
    }


    public int getPoints() {

        return this.points;
    }

    public String getName() {

        return this.userName;
    }

}
