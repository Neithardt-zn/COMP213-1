import java.util.Scanner;

/**
 * Created by Lumia on 2017/11/11.
 */
public class HumanPlayer extends Player {

    private Scanner input;
    public HumanPlayer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);
    }

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
