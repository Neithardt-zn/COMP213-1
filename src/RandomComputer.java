/**
 * Created by Lumia on 2017/11/11.
 */
public class RandomComputer extends Player {

    public RandomComputer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);
    }

    @Override
    public Attribute chooseAttribute() {
        return super.getTop().getAttributes().get((int) (Math.random() * super.getNumOfAttributes()));
    }
}
