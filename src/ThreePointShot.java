package src;

public class ThreePointShot extends Shot {
    private double distance;

    public ThreePointShot(boolean made, int quarter, double distance) {
        super(made, checkDistance(quarter, distance));
        this.distance = distance;
    }

    private static int checkDistance(int quarter, double distance) {
        if (distance < 6.75) {
            throw new IllegalArgumentException("3PT distance must be at least 6.75m.");
        }
        return quarter;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public String getType() {
        return "3PT";
    }

    @Override
    public String toString() {
        return super.toString() + " | " + distance + "m";
    }

}
