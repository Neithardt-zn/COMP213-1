/**
 * This class holds the name and value of attributes, and the object of this class
 * will be added to card when card is created
 */
public class Attribute implements Comparable<Attribute> {

    private String name;
    private int value;

    /**
     *
     * @param name
     * @param value
     */
    public Attribute(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Attribute(String name) {
        this(name, 0);
    }

    public Attribute(int value) {
        this("empty", value);
    }

    public void print() {
        System.out.println("------" + name + " value = " + value);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Attribute attribute) {
        if (this.getValue() < attribute.getValue())
            return -1;
        if (this.getValue() > attribute.getValue())
            return 1;
        return 0;
    }
}
