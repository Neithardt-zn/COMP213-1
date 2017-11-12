/**
 * This class extends Player class and is used to create Random Computer which always choose attribute randomly
 */
public class RandomComputer extends Player {

    /**
     * Create an object of Random Computer and call super constructor
     * @param name Name of computer
     * @param numOfCards Number of cards
     * @param numOfAttributes Number of attributes
     * @param warrior Whether it use warrior deck
     */
    public RandomComputer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);
    }

    /**
     * Overrides abstract method extends from super class
     * Always choose attribute randomly
     * @return Chosen attribute
     */
    @Override
    public Attribute chooseAttribute() {
        return super.getTop().getAttributes().get((int) (Math.random() * super.getNumOfAttributes()));
    }
}
