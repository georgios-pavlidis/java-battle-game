import java.util.Random;
import java.util.ArrayList;
public class Monster {

    private ArrayList<Move> moves;
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defence;
    private MonsterType type;

    int originalAttack;

    Random random = new Random();
    //τα τερατα
    Monster (String name,int hp, int attack, int defence, MonsterType type){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defence = defence;
        this.originalAttack = attack;
        this.moves= new ArrayList<>();
        this.type = type;



    }

    public void addMove(Move move){
        moves.add(move);
    }

    public ArrayList<Move> getMoves(){
        return moves;
    }

    public String getName(){
        return name;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefence(){
        return defence;
    }

    public int getHp(){
        return hp;
    }

    public void setName(String name){
        this.name = name;
    }

    public MonsterType getType(){return type;}

    //υπολογισμος για το damage
    void takeDamage(int damage){
        hp = hp - damage;

        if (hp < 0){
            hp = 0;
        }
    }
    boolean isAlive(){
        return hp > 0;
    }

    int calculateDamage(int attackPower, Monster target) {


        int damage = attackPower - target.defence;

        if (damage < 1) {
            damage = 1;
        }

        int chance = random.nextInt(100);

        if (chance < 20){
            damage = damage * 2;
            System.out.println("CRITICAL HIT!");
        }

        return damage;
    }
    public double typeEffectiveness(MonsterType moveType, MonsterType enemyType) {
        // δικός σου κώδικας
        if(moveType == MonsterType.FIRE && enemyType == MonsterType.GRASS){
            return 1.5;
        }

        if (moveType == MonsterType.WATER && enemyType == MonsterType.FIRE) {
            return 1.5;
        }

        if (moveType == MonsterType.GRASS && enemyType == MonsterType.WATER) {
            return 1.5;
        }

        if(moveType == MonsterType.ELECTRIC && enemyType == MonsterType.WATER){
            return 1.5;
        }

        if (moveType == MonsterType.ICE && enemyType == MonsterType.GRASS) {
            return 1.5;
        }

        if (moveType == MonsterType.FIRE && enemyType == MonsterType.ICE) {
            return 1.5;
        }
        if (moveType == MonsterType.WATER && enemyType == MonsterType.GRASS) {
            return 0.5;
        }

        if (moveType == MonsterType.GRASS && enemyType == MonsterType.FIRE) {
            return 0.5;
        }

        if (moveType == MonsterType.ELECTRIC && enemyType == MonsterType.GRASS) {
            return 0.5;
        }

        if (moveType == MonsterType.FIRE && enemyType == MonsterType.WATER) {
            return 0.5;
        }

        if (moveType == MonsterType.ICE && enemyType == MonsterType.FIRE) {
            return 0.5;
        }



        return 1.0;
    }


    int lowerAttack(Move move, Monster target){

        System.out.println("Original attack: " + target.originalAttack);
        System.out.println("Move power: " + move.getPower());


        int reduction = (int)(target.originalAttack * (move.getPower() / 100.0));
        target.attack = target.attack - reduction;

        if (target.attack < 1) {
            target.attack = 1;
        }

        return reduction;
    }

    int useMove(Move move, Monster target) {

        int accuracyRoll = random.nextInt(100);

        if (accuracyRoll >= move.getAccuracy()) {
            return 0;
        }

        int attackPower = attack + move.getPower();


        int damage = calculateDamage(attackPower, target);
        double effectiveness = typeEffectiveness(move.getMonsterMoveType(), target.getType());
        double stab = 1.0;

        if (this.type == move.getMonsterMoveType()) {
            stab = 1.2;
        }


        damage = (int) Math.round(damage * effectiveness * stab);
        target.takeDamage(damage);
        if (effectiveness > 1.0) {
            System.out.println("It's super effective!");
        } else if (effectiveness < 1.0) {
            System.out.println("It's not very effective...");
        }


        return damage;
    }

    public int heal(int amount) {

        int oldHp = hp;

        hp = hp + amount;

        if (hp > maxHp) {
            hp = maxHp;
        }

        return hp - oldHp;
    }
}
