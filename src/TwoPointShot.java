package src;

public class TwoPointShot extends Shot {

    public TwoPointShot(boolean made, int quarter) {
        super(made, quarter);
    }

    @Override
    public int getValue() {
        return 2;
    }

    @Override
    public String getType() {
        return "2PT";
    }

}