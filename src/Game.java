import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The Game class which controls the card game process
 * It used to create a new card game
 */
public class Game {

    /**
     * private field
     * Holds an ArrayList of players
     * Holds the round number
     * Holds Scanner to read the user input
     * Holds number of attributes
     */
    private ArrayList<Player> players;
    private int round = 1;
    private Scanner input;
    private int numOfAttributes;
    /**
     * To create a new Game, add corresponding number of players and computers to players ArrayList.
     * @param numOfCards Number of cards each player holds
     * @param numOfAttributes Number of attributes each card holds
     * @param numOfPlayers Number of human players
     * @param numOfRandomComputer Number of Random Computers
     * @param numOfSmartComputer Number of Smart Computers
     * @param numOfPredictableComputer Number of Predictable Computers
     * @param numOfCheatingComputer Number of Cheating Computers
     * @param warrior If use warrior deck
     */
    public Game(int numOfCards,
                int numOfAttributes,
                int numOfPlayers,
                int numOfRandomComputer,
                int numOfSmartComputer,
                int numOfPredictableComputer,
                int numOfCheatingComputer,
                boolean warrior
                ) {
        this.numOfAttributes = numOfAttributes;
        input = new Scanner(System.in);
        players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Please enter the name of player " + String.valueOf(i + 1));
            String nameOfPlayer = input.nextLine();
            HumanPlayer player = new HumanPlayer(nameOfPlayer, numOfCards, numOfAttributes, warrior);
            players.add(player);
        }

        for (int i = 0; i < numOfRandomComputer; i++) {
            RandomComputer randomComputer = new RandomComputer("Random Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes, warrior);
            players.add(randomComputer);
        }

        for (int i = 0; i < numOfSmartComputer; i++) {
            SmartComputer smartComputer = new SmartComputer("Smart Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes, warrior);
            players.add(smartComputer);
        }

        for (int i = 0; i < numOfPredictableComputer; i++) {
            PredictableComputer predictableComputer = new PredictableComputer("Predictable Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes, warrior);
            players.add(predictableComputer);
        }

        for (int i = 0; i < numOfCheatingComputer; i++) {
            CheatingComputer cheatingComputer = new CheatingComputer("Cheating Computer " + String.valueOf(i + 1),
                    numOfCards, numOfAttributes, warrior);
            players.add(cheatingComputer);
        }

    }

    /**
     * private method that only be used in this.start() method, choose one player to play with an attribute and
     * decide who is the winner
     * @param currentPlayer the index of random player
     * @return Winner in this round
     */
    private Player play(int currentPlayer) {
        Attribute chosenAttribute = players.get(currentPlayer).chooseAttribute();
        System.out.println(chosenAttribute.getName() + " has been chosen");
        ArrayList<Player> winPlayers = new ArrayList<Player>();
        Attribute maxAttribute = chosenAttribute;
        try {
            for (Player player : players) {                                                                             //Calculate the max value of chosen attribute
                Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
                if (maxAttribute.compareTo(currentAttribute) < 0)
                    maxAttribute = currentAttribute;
            }
            System.out.println("Enter any key to show all top cards of all players");
            input.nextLine();
            System.out.println("Top cards of all players are:");
            for (Player player : players) {                                                                             //Show all players top cards and choose the potential
                                                                                                                        //winners
                System.out.println(player.getName() + ": ");
                player.getTop().print();
                Attribute currentAttribute = player.getTop().getChosenAttribute(chosenAttribute);
                if (maxAttribute.compareTo(currentAttribute) == 0)
                    winPlayers.add(player);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (winPlayers.size() > 1) {
            String output = "";
            for (Player player : winPlayers) {
                output += (player.getName() + ", ");
            }
            System.out.println(output + "has same max value attribute, choose the winner randomly...");
        }
        Player winPlayer = winPlayers.get((int) (Math.random() * winPlayers.size()));                                   //If there are more than one potential winners,
        System.out.println(winPlayer.getName() + " is the winner in this round");                                       //choose randomly
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

    /**
     * Start the game, when there is only one player exist, game finish
     * In each round, randomly choose one player to play, the player choose the attribute and compare this attribute
     * each player's top card hold.
     * The biggest value of player will win
     * If there are many players hold same biggest value, randomly choose one winner
     */
    public void start() {
        Player randomPlayer;
        if (players.size() <= 1) {
            System.out.println("Game can not be started, because there are not enough players");
            return;
        }
        System.out.println("Game start");
        Player winPlayer = players.get(0);
        while (players.size() > 1) {
            System.out.println("This is round " + round);
            randomPlayer = players.get((int)(Math.random() * players.size()));                                          //Randomly choose next player
            for (Player showPlayer : players) {
                System.out.println(showPlayer.getName() + " has " + showPlayer.getNumCards() + " cards");
            }
            System.out.println("=========================" + "\nIt's " + randomPlayer.getName() + " turn\n=========================" +
                    "\nPlease enter any key to continue");
            input.nextLine();
            winPlayer = this.play(players.indexOf(randomPlayer));
            if (winPlayer == null) {                                                                                    //If winner is null, the exception must occur
                return;
            }
            round++;
            System.out.println("Enter any key to go next round");
            input.nextLine();
        }
        System.out.println("Game finish, " + winPlayer.getName() + " is the final winner");
    }

    /**
     * Inner class extends the abstract class Player, it used to create a cheating computer which has information of all players' top card and
     * always choose optimal action by reward.
     */
    class CheatingComputer extends Player {

        /**
         * To construct CheatingComputer object, receive 4 arguments and call super constructor
         * @param name The name of Cheating Computer
         * @param numOfCards The number of cards it holds at start of game
         * @param numOfAttributes The number of attributes it holds
         * @param warrior If use warrior deck
         */
        public CheatingComputer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
            super(name, numOfCards, numOfAttributes, warrior);
        }

        /**
         * The override method, it overrides the abstract method extends from Player class
         * The method is used to choose an attribute to play with optimally
         * @return The attribute it chooses
         */
        @Override
        public Attribute chooseAttribute() {

            int maxAction = 0;                                  //The action which has most reward
            double maxReward = 0;                               //The max reward
            int maxCardValue;                                   //The max value in each attribute
            double tempReward;                                  //Temporary reward
            int sameValuePlayers;
            for(int i = 0; i < numOfAttributes; i++) {                                          //Suppose the computer choose attribute i
                sameValuePlayers = 0;
                tempReward = 0;
                maxCardValue = 0;
                for (Player player : players) {                                                 //Calculate the max value
                    if (maxCardValue < player.getTop().getAttributes().get(i).getValue())
                        maxCardValue = player.getTop().getAttributes().get(i).getValue();
                }
                for (Player player : players) {                                                 //If this object has max value of attribute i,
                                                                                                //add 1000 reward (ensure the biggest probability to win this round)
                    if (maxCardValue == player.getTop().getAttributes().get(i).getValue()) {    //Else minus number of cards that winner(randomly) holds for rewards
                        sameValuePlayers++;                                                     //to avoid player who already has many cards winning this round
                        if (player.equals(this))
                            tempReward += 1000;
                        else tempReward -= player.getNumCards();
                    }
                }
                tempReward /= sameValuePlayers;                                                 //Reward should be dived by number of max value holder, because they have
                                                                                                //same probability to be chosen as winner
                if (maxReward < tempReward) {
                    maxReward = tempReward;
                    maxAction = i;
                }
            }
            return this.getTop().getAttributes().get(maxAction);

        }
    }

}

