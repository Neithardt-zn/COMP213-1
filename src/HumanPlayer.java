/**
 * Created by Lumia on 2017/11/11.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, int numOfCards, int numOfAttributes) {
        super(name, numOfCards, numOfAttributes);
    }

    public Attribute chooseAttributes() {
        System.out.println("Please choose the attributes you want to play with");
        super.getTop().print();
        int numAttribute = Integer.parseInt(System.console().readLine());
        return super.getTop().getAttributes().get(numAttribute);
    }

}
