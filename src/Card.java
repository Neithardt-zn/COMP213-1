import java.util.ArrayList;

/**
 * @author Neng Zhang
 * @version 1.0
 */
public class Card {
    private final String name;
    private final ArrayList<Attribute> attributes;
    private final int numAttributes;



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

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public Attribute getChosenAttribute(Attribute chosenAttribute) throws IllegalArgumentException{
        for (Attribute attribute : attributes) {
            if (attribute.getName().equals(chosenAttribute.getName()))
                return attribute;
        }
        throw new IllegalArgumentException("No such attribute");
    }

}
