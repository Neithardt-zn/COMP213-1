import java.util.ArrayList;

/**
 * This Class holds the name, all attributes, number of attributes of card
 */
public class Card {
    /**
     * private and final, hold name of card, An ArrayList<Attribute> attributes, and the number of attributes in this card
     * When this object is created, the private final field is initialized and can't be changed
     */
    private final String name;
    private final ArrayList<Attribute> attributes;
    private final int numAttributes;

    /**
     * To create the object of Card class, and there are two deck of card can be created.
     * Each card have numbers of random value attributes
     * The constructor receives three parameters
     * @param nameOfCard The name of card
     * @param numAttributes The number of attributes
     * @param warrior  If use warrior deck
     */
    public Card(String nameOfCard, int numAttributes, boolean warrior) {
        if (warrior) {
            this.name = nameOfCard;
            this.numAttributes = 4;
            attributes = new ArrayList<Attribute>(numAttributes);
            int value1 = (int) (Math.random() * 10);
            int value2 = (int) (Math.random() * 10);
            int value3 = (int) (Math.random() * 10);
            int value4 = (int) (Math.random() * 10);
            attributes.add(new Attribute("Strength", value1));
            attributes.add(new Attribute("Health Point", value2));
            attributes.add(new Attribute("Armor", value3));
            attributes.add(new Attribute("Speed", value4));

        }
        else{
            this.numAttributes = numAttributes;
            this.name = nameOfCard;
            attributes = new ArrayList<Attribute>(numAttributes);
            for (int i = 0; i < numAttributes; i++) {
                String name = "Attributes " + String.valueOf(i + 1);
                int value = (int) (Math.random() * 10);
                attributes.add(new Attribute(name, value));
            }
        }
    }

    /**
     * Get the card name
     * @return Card name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the number of attributes
     * @return Number of attributes
     */
    public int getNumAttributes() {
        return this.numAttributes;
    }

    /**
     * Print all attributes of card
     */
    public void print() {
        System.out.println("---" + name);
        for (Attribute attribute : attributes) {
            attribute.print();
        }
    }

    /**
     * Get the ArrayList of attributes the card holds
     * @return ArrayList of attributes
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Receive an object of Attribute, and choose the same attribute this card holds
     * @param chosenAttribute The attribute it want to choose
     * @return The same attribute this card holds
     * @throws IllegalArgumentException when the same attribute can't be found, it will throw and exception
     */
    public Attribute getChosenAttribute(Attribute chosenAttribute) throws IllegalArgumentException{
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(chosenAttribute.getName()))
                return attribute;
        }
        throw new IllegalArgumentException("No such attribute");
    }

}
