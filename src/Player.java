import java.util.ArrayList;

/**
 * Created by Lumia on 2017/11/6.
 */
public class Player {

    private ArrayList<Card> cards;
    private int numOfAttributes;
    private int numCards;
    private String name;

    public Player(String name, int numCards, int numOfAttributes) {
        this.name = name;
        this.numCards = numCards;
        this.numOfAttributes = numOfAttributes;
        for (int i = 0; i < this.numCards; i++) {
            cards = new ArrayList<Card>(this.numCards);
            cards.add(new Card(name + ": Card" + String.valueOf(i), this.numOfAttributes));

        }
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card getTop() {
        return this.cards.get(0);

    }

    public Card removeCard() {
        Card removedCard = this.cards.get(0);
        this.cards.remove(0);
        return removedCard;
    }

    public Attribute chooseAttribute() {
        return this.getTop().getAttributes().get(0);
    }

    public String getName() {
        return name;
    }

    public int getNumCards() {
        return numCards;
    }

    public int getNumOfAttributes() {
        return numOfAttributes;
    }

}
