import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    //player move selection
    static void showPlayerMoves(Monster player) {

        System.out.println("Choose your attack:");

        for (int i = 0; i < player.getMoves().size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                            player.getMoves().get(i).getName()
            );
        }
    }

    //εμφανιση hp
    static void showHP(Monster player, Monster enemy){
        System.out.println(player.getName() + " hp " + player.getHp());
        System.out.println(enemy.getName() + " HP " + enemy.getHp());
    }

    static void executeMove(Monster attacker, Monster target, Move chosenMove) {
        switch (chosenMove.getType()){
            case ATTACK:
                int damage = attacker.useMove(chosenMove, target);

                System.out.println(attacker.getName() + " used " + chosenMove.getName() + "!");

                if (damage > 0){
                    System.out.println("Damage " + damage);
                } else {
                    System.out.println("Attack Missed");
                }
                break;

            case LOWER_ATTACK:
                int reduction = attacker.lowerAttack(chosenMove, target);

                System.out.println(attacker.getName() + " used " + chosenMove.getName() + "!");
                System.out.println(target.getName() + "'s Attack fell by " + reduction + "!");

                break;

            case HEAL:

                int healed = attacker.heal(chosenMove.getPower());

                System.out.println(attacker.getName() + " used " + chosenMove.getName() + "!");
                System.out.println(attacker.getName() + " recovered " + healed + " HP!");

                break;



        }
    }

    //enemy move selection
    static void enemyTurn(Monster enemy, Monster player) {

        Random random = new Random();
        int moveIndex = random.nextInt(enemy.getMoves().size());

        Move chosenMove = enemy.getMoves().get(moveIndex);

        executeMove(enemy, player, chosenMove);
    }

    public static void main(String[] args) {

        //move creation
        // PYROS MOVES
        Move slash = new Move("Slash", 0, 100, MoveType.ATTACK);
        Move emberClaw = new Move("Ember Claw", 5, 90, MoveType.ATTACK);
        Move flameBurst = new Move("Flame Burst", 12, 75, MoveType.ATTACK);

        // PAGOS MOVES
        Move iceShard = new Move("Ice Shard", 0, 100, MoveType.ATTACK);
        Move frostBite = new Move("Frost Bite", 5, 90, MoveType.ATTACK);
        Move blizzard = new Move("Blizzard", 12, 75, MoveType.ATTACK);

        // XORTOS MOVES
        Move vineWhip = new Move("Vine Whip", 0, 100, MoveType.ATTACK);
        Move leafBlade = new Move("Leaf Blade", 5, 90, MoveType.ATTACK);
        Move seedBomb = new Move("Seed Bomb", 12, 75, MoveType.ATTACK);

        // ELEKOS MOVES
        Move spark = new Move("Spark", 0, 100, MoveType.ATTACK);
        Move thunderFang = new Move("Thunder Fang", 5, 90, MoveType.ATTACK);
        Move lightningBolt = new Move("Lightning Bolt", 12, 75, MoveType.ATTACK);

        Move growl = new Move("Growl", 0, 100, MoveType.LOWER_ATTACK);

        Move recover = new Move("Recover", 30, 100, MoveType.HEAL);


        //creation of list of monsters
        ArrayList<Monster> monsters = new ArrayList<>();

        //input from player
        Scanner scanner = new Scanner(System.in);

        //moster creation
        Monster pyros = new Monster("Pyros",100,36,12);
        Monster pagos = new Monster("Pagos",100,28,23);
        Monster xortos = new Monster("Xortos",100,33,18);
        Monster elekos = new Monster("Elekos",100,40,8);

        //monsters being added to the list
        monsters.add(pyros);
        monsters.add(pagos);
        monsters.add(xortos);
        monsters.add(elekos);

        //monsters gettin moves
        pyros.addMove(slash);
        pyros.addMove(emberClaw);
        pyros.addMove(flameBurst);

        pagos.addMove(iceShard);
        pagos.addMove(frostBite);
        pagos.addMove(blizzard);
        pagos.addMove(recover);

        xortos.addMove(vineWhip);
        xortos.addMove(leafBlade);
        xortos.addMove(seedBomb);

        elekos.addMove(spark);
        elekos.addMove(thunderFang);
        elekos.addMove(lightningBolt);

        for (int i = 0; i < monsters.size(); i++){
            System.out.println((i + 1) + ". " + monsters.get(i).getName());
        }

        int monsterChoice = scanner.nextInt();
        scanner.nextLine();

        Monster player = monsters.get(monsterChoice -1);

        System.out.println("You choose " + player.getName() + "!");

        //κλαση για τα τερατα


        Random randomEn = new Random();

        int enemyIndex = randomEn.nextInt(monsters.size());

        while (enemyIndex == monsterChoice - 1){
            enemyIndex = randomEn.nextInt(monsters.size());
        }

        Monster enemy = monsters.get(enemyIndex);

        System.out.println("Enemy chose " + enemy.getName() + "!");

        //τρεξιμο του παιχνιδιου
        while (player.isAlive() && enemy.isAlive()) {


            //αυτο ειναι για να σταματαει μετα απο μια απο τις επιθεσεις
            boolean validChoice = false;
            while (!validChoice) {
                showPlayerMoves(player);

                //αυτο ειναι για την επιλογη επιθεσης
                int choice = scanner.nextInt();
                scanner.nextLine();

                Move chosenMove = player.getMoves().get(choice -1);

                executeMove(player, enemy, chosenMove);

                if (!enemy.isAlive()) {
                    System.out.println(player.getName() + " wins!");
                    break;

                }
            }

            if (!enemy.isAlive()) {
                break;
            }
            //επιλογη επιθεσης αντιπαλου
            enemyTurn(enemy, player);

            if (!player.isAlive()) {
                System.out.println(enemy.getName() + " wins!");
                break;
            }

            showHP(player, enemy);

            System.out.println("Press ENTER for next round...");
            scanner.nextLine();

            System.out.println("----------------");

        }


    }


}