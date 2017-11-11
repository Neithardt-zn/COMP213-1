/**
 * Created by lumia on 2017/11/11.
 */
public class PredictableComputer extends Player {

    public PredictableComputer(String name, int numOfCards, int numOfAttributes) {
        super(name, numOfCards, numOfAttributes);
    }

    @Override
    public Attribute chooseAttribute() {
        return super.getTop().getAttributes().get(0);
    }
}