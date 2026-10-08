public class Move {
    private String name;
    private int power;
    private int accuracy;

    private MoveType type;

    public Move(String name, int power, int accuracy, MoveType type){
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public int getPower(){
        return power;
    }

    public int getAccuracy(){
        return accuracy;
    }

    public MoveType getType(){
        return type;
    }
}
