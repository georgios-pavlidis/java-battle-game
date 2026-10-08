import java.util.Random;
import java.util.ArrayList;
public class Monster {

    private ArrayList<Move> moves;
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defence;

    int originalAttack;

    Random random = new Random();
    //τα τερατα
    Monster (String name,int hp, int attack, int defence){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defence = defence;
        this.originalAttack = originalAttack;

        this.moves= new ArrayList<>();


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


    int lowerAttack(Move move, Monster target){
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

        target.takeDamage(damage);

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
