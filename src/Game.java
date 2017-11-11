import java.util.ArrayList;

/**
 * Created by Lumia on 2017/11/11.
 */
public class Game {

    private ArrayList<Player> players;
    private int round = 0;
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

    public void nextRound() {

    }



}
