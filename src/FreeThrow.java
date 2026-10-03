package src;

public class FreeThrow extends Shot {

    public FreeThrow(boolean made, int quarter) {
        super(made, quarter);
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public String getType() {
        return "FT";
    }

}
