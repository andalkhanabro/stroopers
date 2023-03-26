package persistence;

import model.Score;
import model.ScoreBoard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads scoreboard from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads scoreboard from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ScoreBoard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreBoard(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses scoreboard from JSON object and returns it
    private ScoreBoard parseScoreBoard(JSONObject jsonObject) {
        ScoreBoard sb = new ScoreBoard();
        addScores(sb, jsonObject);
        return sb;
    }

    // MODIFIES: sb
    // EFFECTS: parses thingies from JSON object and adds them to scoreboard
    private void addScores(ScoreBoard sb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(sb, nextScore);
        }
    }

    // MODIFIES: sb
    // EFFECTS: parses score from JSON object and adds it to scoreboard
    private void addScore(ScoreBoard sb, JSONObject jsonObject) {
        String userName = jsonObject.getString("name");
        int points = Integer.parseInt((jsonObject.getString("points")));
        Score score = new Score(userName, points);
        sb.addScore(score);
    }

}

