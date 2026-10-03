package src;

public class Game {
    private Team home;
    private Team away;

    public Game(Team home, Team away) {
        if (home == null || away == null) {
            throw new IllegalArgumentException("Teams cannot be null.");
        }
        if (home == away) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }
        this.home = home;
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public Team getWinner() {
        int homePoints = home.getTotalPoints();
        int awayPoints = away.getTotalPoints();
        if (homePoints > awayPoints) {
            return home;
        } else if (awayPoints > homePoints) {
            return away;
        } else {
            return null; // Tie
        }
    }

    public String getScoreboard() {
        String text = "";
        text += home.getName() + " " + home.getTotalPoints() + " - " + away.getTotalPoints() + " " + away.getName()
                + "\n";
        text += "Quarters: ";
        for (int i = 1; i <= 4; i++) {
            text += home.getPointInQuarter(i) + "-" + away.getPointInQuarter(i);
            if (i < 4) {
                text += " | ";
            }
        }
        text += "\n";
        text += "Winner: " + (getWinner() != null ? getWinner().getName() : "Tie") + "\n";
        return text;
    }

}
