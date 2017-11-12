import java.util.ArrayList;

/**
 * This class extends Player class and is used to create Smart Computer which always choose max value attribute
 */
public class SmartComputer extends Player {

    /**
     * Create an object of Smart Computer and call super constructor
     * @param name Name of computer
     * @param numOfCards Number of cards
     * @param numOfAttributes Number of attributes
     * @param warrior Whether it use warrior deck
     */
    public SmartComputer(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        super(name, numOfCards, numOfAttributes, warrior);

    }

    /**
     * Overrides abstract method extends from Player class
     * To choose max value attribute
     * @return Chosen attribute
     */
    @Override
    public Attribute chooseAttribute() {
        ArrayList<Attribute> attributes = super.getTop().getAttributes();
        Attribute maxAttribute = attributes.get(0);
        for (Attribute attribute : attributes) {
            if (attribute.compareTo(maxAttribute) > 0)
                maxAttribute = attribute;
        }
        return maxAttribute;
    }

}
