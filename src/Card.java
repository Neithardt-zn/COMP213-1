import java.util.ArrayList;

/**
 * @author Neng Zhang
 * @version 1.0
 */
public class Card {
    private final String name;
    private final ArrayList<Attribute> attributes;
    private final int numAttributes;



    public Card(String nameOfCard, int numAttributes) {
        this.numAttributes = numAttributes;
        this.name = nameOfCard;
        attributes = new ArrayList<Attribute>(numAttributes);
        for (int i = 0; i < numAttributes; i++) {
            String name = "Attributes " + i;
            int value = (int)(Math.random() * 10);
            attributes.add(new Attribute(name, value));
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
        System.out.println("Card: " + name);
        for (Attribute attribute : attributes) {
            attribute.print();
        }
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }



}