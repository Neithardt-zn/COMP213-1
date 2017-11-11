/**
 * Created by Lumia on 2017/11/11.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, int numOfCards, int numOfAttributes) {
        super(name, numOfCards, numOfAttributes);
    }

    @Override
    public Attribute chooseAttribute() {
        System.out.println("Please choose the attributes you want to play with");
        super.getTop().print();
        Boolean valid = false;
        int numAttribute = 0;
        while(!valid) {
            try {
                numAttribute = Integer.parseInt(System.console().readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            }
        }
        return super.getTop().getAttributes().get(numAttribute);
    }

}
