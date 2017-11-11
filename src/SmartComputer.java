import java.util.ArrayList;

/**
 * Created by Lumia on 2017/11/11.
 */
public class SmartComputer extends Player {

    public SmartComputer(String name, int numCards, int numOfAttributes) {
        super(name, numCards, numOfAttributes);

    }

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
