/**
 * This class holds the name and value of attributes, and the object of this class
 * will be added to card when card is created
 * This class implements Comparable<Attribute> interface
 */
public class Attribute implements Comparable<Attribute> {
    /**
     * Hold the name of attribute
     */
    private final String name;
    /**
     * Hold the value of attribute
     */
    private final int value;

    /**
     * To construct an object of Attribute class, name and value should be initialized
     * The constructor receives two parameters
     * @param name The name of attribute
     * @param value The value of attribute
     */
    public Attribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * To print the attribute name and value
     */
    public void print() {
        System.out.println("------" + name + " value = " + value);
    }

    /**
     * Get the value of this attribute
     * @return  The value of attribute
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the name of this attribute
     * @return The name of attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Implement compareTo method of Comparable<Attribute> interface, to compare Attribute object
     * @param otherAttribute The other attribute object which is to compare to
     * @return If this object value is larger than the others, return 1, if less than return -1, if equal return 0
     */
    @Override
    public int compareTo(Attribute otherAttribute) {
        if (this.getValue() < otherAttribute.getValue())
            return -1;
        if (this.getValue() > otherAttribute.getValue())
            return 1;
        return 0;
    }
}
