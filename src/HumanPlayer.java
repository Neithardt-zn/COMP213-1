import java.util.Scanner;

/**
 * Created by Lumia on 2017/11/11.
 */
public class HumanPlayer extends Player {

    private Scanner input;
    public HumanPlayer(String name, int numOfCards, int numOfAttributes) {
        super(name, numOfCards, numOfAttributes);
    }

    @Override
    public Attribute chooseAttribute() {
        input = new Scanner(System.in);
        System.out.println("Please choose the attributes you want to play with");
        super.getTop().print();
        Boolean valid = false;
        int numAttribute = 0;
        while(!valid) {
            try {
                numAttribute = Integer.parseInt(input.nextLine());
                if (numAttribute > this.getNumOfAttributes() || numAttribute <= 0){
                    System.out.println("Please enter valid number");
                    continue;
                }
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            }
        }
        return super.getTop().getAttributes().get(numAttribute - 1);
    }

}
