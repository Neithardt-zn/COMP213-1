import java.util.Scanner;

/**
 * This class is used to interact with users and let users decide the setting of games
 */
public class Main {
    /**
     * private field, Scanner, to get user input
     */
    private static Scanner input = new Scanner(System.in);

    /**
     * main method, start the program and start the interaction with users
     * @param args default arguments
     */
    public static void main(String[] args) {

        int deck = 0;
        int numOfPlayers = 0;
        int numOfCards = 0;
        int numOfAttributes = 0;
        int numOfRandomComputer = 0;
        int numOfSmartComputer = 0;
        int numOfPredictableComputer = 0;
        int numOfCheatingComputer = 0;
        boolean warrior = false;
        //Allow user to configure the setting of game including
        //number of human players, number of cards, number of attributes(if do not use Warrior deck),
        //number of random computers, number of smart computers, number of predictable computers, number of cheating computers,
        //whether use warrior deck
        System.out.println("Welcome to card game!");
        System.out.println("Enter 1 or 2 to choose which deck of cards you want to play\n1--Warrior(Attributes: Strength, Health Point, Armor, Speed\n" +
                "2--Custom deck(Attribute 1, Attribute 2, Attribute 3 etc, you can add 3 to 8 attributes)");
        deck = inputNumber(1, 2);
        System.out.println("Choose the number of human player you want to add, you can add 1 to 6 players");
        numOfPlayers = inputNumber(1, 6);
        System.out.println("Choose the number of cards each player holds at start of game, you can choose 5 to 30 cards");
        numOfCards = inputNumber(5, 30);
        if (deck == 2) {
            System.out.println("Choose the number of attributes each card holds, you can choose 3 to 8 attributes");
            numOfAttributes = inputNumber(3, 8);
            warrior = false;
        }
        else {
            numOfAttributes = 4;
            warrior = true;
        }
        System.out.println("Choose the number of Random Computer(choose attribute randomly) you want to add," +
                " you can add 0 to 3 random computers");
        numOfRandomComputer = inputNumber(0, 3);
        System.out.println("Choose the number of Smart Computer(choose max value attribute) you want to add," +
                " you can add 0 to 3 smart computers");
        numOfSmartComputer = inputNumber(0, 3);
        System.out.println("Choose the number of Predictable Computer(always choose the first attribute) you want to add," +
                " you can add 0 to 3 predictable computers");
        numOfPredictableComputer = inputNumber(0, 3);
        System.out.println("Choose the number of Cheating Computer(know every player's top card, and do the best action) you want to add," +
                " you can add 0 to 3 cheating computers");
        numOfCheatingComputer = inputNumber(0, 3);
        Game game = new Game(numOfCards, numOfAttributes, numOfPlayers, numOfRandomComputer, numOfSmartComputer,
                numOfPredictableComputer, numOfCheatingComputer, warrior);
        game.start();
    }

    /**
     * private method, it is used to get user input about the number of player, attributes etc
     * @param lowerBound The lower bound of number user can input
     * @param upperBound The upper bound of number user can input
     * @return The number user input
     */
    private static int inputNumber(int lowerBound, int upperBound) {
        boolean valid = false;
        int result = 0;
        while (!valid) {
            try {
                result = Integer.parseInt(input.nextLine());
                valid = true;
                if(!(result <= upperBound && result >= lowerBound)){
                    valid = false;
                    System.out.println("Please enter valid number(" + lowerBound + " to " + upperBound +")");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number(" + lowerBound + " to " + upperBound +")");
            }

        }
        return result;
    }
}

