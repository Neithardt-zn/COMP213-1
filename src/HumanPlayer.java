import java.util.Scanner;

/**
 * This class extends Player and is used to create a human player
 * Human player choose the attribute by user input
 */
public class HumanPlayer extends Player {
    /**
     * Scanner, to get user input
     */
    private Scanner input;

    /**
     * The constructor has four arguments and call super constructor
     * @param name The name of human player
     * @param numOfCards The number of cards
     * @param numOfAttributes The number of attributes
     * @param warrior If use warrior deck
     */
    public HumanPlayer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);
    }

    /**
     * Overrides the abstract method extends from Player
     * Show the information of top card and choose attribute by user input
     * @return Chosen attribute
     */
    @Override
    public Attribute chooseAttribute() {
        int upperBound = this.getNumOfAttributes();
        input = new Scanner(System.in);
        System.out.println("Please choose the attributes you want to play with (1 to " + upperBound + ")");
        super.getTop().print();
        Boolean valid = false;
        int numAttribute = 0;
        while(!valid) {
            try {
                numAttribute = Integer.parseInt(input.nextLine());
                valid = true;
                if (numAttribute > upperBound|| numAttribute <= 0){
                    System.out.println("Please enter valid number");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            }
        }
        return super.getTop().getAttributes().get(numAttribute - 1);
    }

}
