public class Move {
    private String name;
    private int power;
    private int accuracy;
    ///
    private MonsterType type1;
    private MoveType type;

    public Move(String name, int power, int accuracy, MoveType type, MonsterType type1){
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.type1 = type1;
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

    public MonsterType getMonsterMoveType(){return type1;}
}
