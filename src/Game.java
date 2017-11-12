import sun.font.TrueTypeFont;

import java.util.ArrayList;

/**
 * Created by Lumia on 2017/11/11.
 */
public class Game {

    private ArrayList<Player> players;
    private int round = 1;

    public Game(int numOfCards,
                int numOfAttributes,
                int numOfPlayers,
                int numOfRandomComputer,
                int numOfSmartComputer,
                int numOfPredictableComputer) {

        players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Please enter the name of player " + String.valueOf(i + 1));
            String nameOfPlayer = System.console().readLine();
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
        }

        for (int i = 0; i < numOfPredictableComputer; i++) {
            PredictableComputer predictableComputer = new PredictableComputer("Predictable Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes);
        }

    }

    public Player play(int currentPlayer) {
        Attribute chosenAttribute = players.get(currentPlayer - 1).chooseAttribute();
        System.out.println("Current attribute is " + chosenAttribute.getName());
        ArrayList<Player> winPlayers = new ArrayList<Player>();
        Attribute maxAttribute = chosenAttribute;
        for (Player player : players) {
            Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
            if (maxAttribute.compareTo(currentAttribute) < 0)
                maxAttribute = chosenAttribute;
        }
        System.out.println("Top cards of all players are:");
        for (Player player : players) {
            player.getTop().print();
            Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
            if (maxAttribute.compareTo(currentAttribute) == 0)
                winPlayers.add(player);
        }
        if (winPlayers.size() > 1) {
            String output = "";
            for (Player player : players) {
                output.concat(player.getName() + ", ");
            }
            System.out.println(output + " has max value attribute, choose the winner randomly...");
        }
        Player winPlayer = winPlayers.get((int) (Math.random() * winPlayers.size()));
        System.out.println(winPlayer.getName() + " is the winner in this round");
        for (Player lostPlayer : players) {
            winPlayer.addCard(lostPlayer.removeTopCard());
            if ((lostPlayer.getNumCards() == 0) & (!winPlayer.equals(lostPlayer))) {
                players.remove(lostPlayer);
                System.out.println(lostPlayer.getName() + " has no cards.");
            }
        }
        return winPlayer;
    }

    public void start() {
        System.out.println("Game start");
        Player winPlayer = players.get(0);
        while (players.size() > 1) {
            System.out.println("This is round " + round);

            for (Player showPlayer : players) {
                System.out.println(showPlayer.getName() + " has " + showPlayer.getNumCards() + " cards");
            }
            System.out.println(winPlayer.getName() + " choose the attribute...");
            winPlayer = this.play(players.indexOf(winPlayer));
        }
        System.out.println("Game finish, " + winPlayer.getName() + " is the final winner");
    }
}

