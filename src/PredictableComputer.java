/**
 * This class extends Player class and is used to create Predictable Computer which always choose the first attribute
 */
public class PredictableComputer extends Player {

    /**
     * Create an object of Predictable Computer and call super constructor
     * @param name Name of computer
     * @param numOfCards Number of cards
     * @param numOfAttributes Number of attributes
     * @param warrior Whether it use warrior deck
     */
    public PredictableComputer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);
    }

    /**
     * Overrides abstract method extends from super class
     * Always choose the first attributes
     * @return Chosen attribute
     */
    @Override
    public Attribute chooseAttribute() {
        return super.getTop().getAttributes().get(0);
    }
}
