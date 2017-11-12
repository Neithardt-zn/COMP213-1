import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Welcome to card game!");
        System.out.println("Choose the number of human player you want to add, you can add 1 to 6 players");
        int numOfPlayers = inputNumber(1, 6);
        System.out.println("Choose the number of cards each player holds at start of game, you can choose 5 to 30 cards");
        int numOfCards = inputNumber(5, 30);
        System.out.println("Choose the number of attributes each card holds, you can choose 1 to 8 attributes");
        int numOfAttributes = inputNumber(1, 8);
        System.out.println("Choose the number of Random Computer(choose attribute randomly) you want to add, you can add 0 to 3 random computers");
        int numOfRandomComputer = inputNumber(0, 3);
        System.out.println("Choose the number of Smart Computer(choose max value attribute) you want to add, you can add 0 to 3 smart computers");
        int numOfSmartComputer = inputNumber(0, 3);
        System.out.println("Choose the number of Predictable Computer(always choose the first attribute) you want to add, you can add 0 to 3 smart computers");
        int numOfPredictableComputer = inputNumber(0, 3);
        Game game = new Game(numOfCards, numOfAttributes, numOfPlayers, numOfRandomComputer, numOfSmartComputer, numOfPredictableComputer);
        game.start();
    }

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

