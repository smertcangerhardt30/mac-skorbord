package src;

import java.util.ArrayList;

public class Team implements Scoreable {
    private String name;
    private ArrayList<Player> players;

    public static final int MAX_PLAYERS = 12;

    public Team(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty.");
        }
        this.name = name;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public void addPlayer(Player p) {
        if (p == null) {
            throw new IllegalArgumentException("Player cannot be null.");
        }
        if (players.size() >= MAX_PLAYERS) {
            throw new IllegalStateException("Team already has maximum number of players.");
        }
        for (Player player : players) {
            if (player.getJerseyNumber() == p.getJerseyNumber()) {
                throw new IllegalArgumentException("A player with this jersey number already exists in the team.");
            }
        }
        players.add(p);

    }

    public Player findPlayer(int jerseyNumber) {
        for (Player p : players) {
            if (p.getJerseyNumber() == jerseyNumber) {
                return p;
            }
        }
        return null;
    }

    public int getTotalPoints() {
        int totalPoints = 0;
        for (Player p : players) {
            totalPoints += p.getTotalPoints();
        }
        return totalPoints;
    }

    public int getPointInQuarter(int q) {
        if (q < 1 || q > 4) {
            throw new IllegalArgumentException("Quarter must be between 1 and 4.");
        }
        int totalPoints = 0;
        for (Player p : players) {
            totalPoints += p.getPointsInQuarter(q);
        }
        return totalPoints;
    }

    public Player getTopScorer() {
        if (players.isEmpty()) {
            return null;
        }
        Player topScorer = players.get(0);
        for (Player p : players) {
            if (p.getTotalPoints() > topScorer.getTotalPoints()) {
                topScorer = p;
            }
        }
        return topScorer;
    }
}
