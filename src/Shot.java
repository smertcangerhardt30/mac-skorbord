package src;

public abstract class Shot {
    private boolean made;
    private int quarter;
    private static int totalShots;

    public Shot(boolean made, int quarter) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Quarter must be between 1 and 4.");
        }
        this.made = made;
        this.quarter = quarter;
        totalShots++;
    }

    public boolean isMade() {
        return made;
    }

    public int getQuarter() {
        return quarter;
    }

    public static int getTotalShots() {
        return totalShots;
    }

    public abstract int getValue();

    public abstract String getType();

    public int getPoints() {
        if (made) {
            return getValue();
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Q" + getQuarter() + " | " + getType() + " | " + (made ? "Made" : "Missed");
    }

}
