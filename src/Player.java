package src;

import java.util.ArrayList;
import java.util.Locale;

public class Player implements Scoreable {
    private String name;
    private int jerseyNumber;
    private ArrayList<Shot> shots;
    private int fouls;

    public static final int MAX_FOULS = 5;

    public Player(String name, int jerseyNumber) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (jerseyNumber < 0 || jerseyNumber > 99) {
            throw new IllegalArgumentException("Jersey number must be between 0 and 99.");
        }
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.shots = new ArrayList<>();
        this.fouls = 0;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getFouls() {
        return fouls;
    }

    public boolean isFouledOut() {
        return fouls >= MAX_FOULS;
    }

    public void addFoul() {
        if (isFouledOut()) {
            throw new IllegalStateException("Player has already fouled out.");
        }
        fouls++;
    }

    public void addShot(Shot s) {
        if (s == null) {
            throw new IllegalArgumentException("Shot cannot be null.");
        }
        if (isFouledOut()) {
            throw new IllegalStateException("Player has fouled out and cannot take more shots.");
        }
        shots.add(s);
    }

    public int getTotalPoints() {
        int totalPoints = 0;
        for (Shot shot : shots) {
            totalPoints += shot.getPoints();
        }
        return totalPoints;
    }

    public int getPointsInQuarter(int q) {
        if (q < 1 || q > 4) {
            throw new IllegalArgumentException("Quarter must be between 1 and 4.");
        }
        int points = 0;
        for (Shot shot : shots) {
            if (shot.getQuarter() == q) {
                points += shot.getPoints();
            }
        }
        return points;
    }

    public int getShotsTaken() {
        return shots.size();
    }

    public int getShotsMade() {
        int madeShots = 0;
        for (Shot shot : shots)
            if (shot.isMade())
                madeShots++;
        return madeShots;
    }

    public double getShotPercentage() {
        if (shots.isEmpty()) {
            return 0.0;
        }
        return (double) getShotsMade() / getShotsTaken() * 100;
    }

    public int countMadeThrees() {
        int count = 0;
        for (Shot shot : shots) {
            if (shot instanceof ThreePointShot && shot.isMade()) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        return String.format(Locale.US, "#%d %s - %d pts (%.1f%%)", getJerseyNumber(), getName(), getTotalPoints(),
                getShotPercentage());
    }

}
