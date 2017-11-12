import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Lumia on 2017/11/11.
 */
public class Game {

    private ArrayList<Player> players;
    private int round = 1;
    private Scanner input;

    public Game(int numOfCards,
                int numOfAttributes,
                int numOfPlayers,
                int numOfRandomComputer,
                int numOfSmartComputer,
                int numOfPredictableComputer) {

        input = new Scanner(System.in);
        players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Please enter the name of player " + String.valueOf(i + 1));
            String nameOfPlayer = input.nextLine();
            HumanPlayer player = new HumanPlayer(nameOfPlayer, numOfCards, numOfAttributes);
            players.add(player);
        }

        for (int i = 0; i < numOfRandomComputer; i++) {
            RandomComputer randomComputer = new RandomComputer("Random Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes);
            players.add(randomComputer);
        }

        for (int i = 0; i < numOfSmartComputer; i++) {
            SmartComputer smartComputer = new SmartComputer("Smart Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes);
            players.add(smartComputer);
        }

        for (int i = 0; i < numOfPredictableComputer; i++) {
            PredictableComputer predictableComputer = new PredictableComputer("Predictable Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes);
            players.add(predictableComputer);
        }

    }

    public Player play(int currentPlayer) {
        Attribute chosenAttribute = players.get(currentPlayer).chooseAttribute();
        System.out.println(chosenAttribute.getName() + " has been chosen");
        ArrayList<Player> winPlayers = new ArrayList<Player>();
        Attribute maxAttribute = chosenAttribute;
        for (Player player : players) {
            Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
            if (maxAttribute.compareTo(currentAttribute) < 0)
                maxAttribute = currentAttribute;
        }
        System.out.println("Enter any key to show all top cards of all players");
        input.nextLine();
        System.out.println("Top cards of all players are:");
        for (Player player : players) {
            System.out.println(player.getName() + ": ");
            player.getTop().print();
            Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
            if (maxAttribute.compareTo(currentAttribute) == 0)
                winPlayers.add(player);
        }
        if (winPlayers.size() > 1) {
            String output = "";
            for (Player player : winPlayers) {
                output += (player.getName() + ", ");
            }
            System.out.println(output + "has same max value attribute, choose the winner randomly...");
        }
        Player winPlayer = winPlayers.get((int) (Math.random() * winPlayers.size()));
        System.out.println(winPlayer.getName() + " is the winner in this round");
        ArrayList<Player> removedPlayer = new ArrayList<Player>();
        for (Player lostPlayer : players) {
            winPlayer.addCard(lostPlayer.removeTopCard());
            if ((lostPlayer.getNumCards() == 0) & (!winPlayer.equals(lostPlayer))) {
                removedPlayer.add(lostPlayer);
                System.out.println(lostPlayer.getName() + " has no cards.");
            }
        }
        players.removeAll(removedPlayer);
        return winPlayer;
    }

    public void start() {
        System.out.println("Game start");
        Player winPlayer = players.get(0);
        while (players.size() > 1) {
            System.out.println("This is round " + round);
            Player randomPlayer = players.get((int)(Math.random() * players.size()));
            for (Player showPlayer : players) {
                System.out.println(showPlayer.getName() + " turn " + " has " + showPlayer.getNumCards() + " cards");
            }
            System.out.println("=========================" + "\nIt's " + randomPlayer.getName() + " turn\n=========================" +
                    "\nPlease enter any key to continue");
            input.nextLine();
            winPlayer = this.play(players.indexOf(randomPlayer));
            round++;
            System.out.println("Enter any key to go next round");
            input.nextLine();
        }
        System.out.println("Game finish, " + winPlayer.getName() + " is the final winner");
    }
}

