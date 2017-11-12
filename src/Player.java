import java.util.ArrayList;

/**
 * The abstract class Player has one abstract method and can be extends by human players, random computers etc
 */
public abstract class Player{

    /**
     * Hold an ArrayList of cards
     */
    private ArrayList<Card> cards;
    /**
     * Number of attributes in cards which player holds
     */
    private int numOfAttributes;
    /**
     * Number of cards player holds
     */
    private int numCards;
    /**
     * Name of player
     */
    private String name;

    /**
     * To construct an object of Player, initialize the player name, number of cards, number of attributes and whether
     * it use warrior deck
     * @param name The name of player
     * @param numOfCards The number of cards player holds
     * @param numOfAttributes The number of attributes
     * @param warrior Whether use warrior deck
     */
    public Player(String name, int numOfCards, int numOfAttributes, boolean warrior) {
        this.name = name;
        this.numCards = numOfCards;
        this.numOfAttributes = numOfAttributes;
        cards = new ArrayList<Card>(this.numCards);
        for (int i = 0; i < this.numCards; i++) {
            cards.add(new Card( "Card " + String.valueOf(i + 1), this.numOfAttributes, warrior));

        }
    }

    /**
     * Add card and increase the number of cards
     * @param card Card object
     */
    public void addCard(Card card) {
        this.cards.add(card);
        numCards++;
    }

    /**
     * Get the top card
     * @return Top card
     */
    public Card getTop() {
        return this.cards.get(0);

    }

    /**
     * Remove the top card and decrease the number of cards
     * @return Top card
     */
    public Card removeTopCard() {
        Card removedCard = this.cards.get(0);
        this.cards.remove(0);
        numCards--;
        return removedCard;
    }

    /**
     * Abstract method
     * @return chosen attribute
     */
    public abstract Attribute chooseAttribute();

    /**
     * Get name of player
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Get number of cards
     * @return Number of cards
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * Get number of attributes
     * @return Number of attributes
     */
    public int getNumOfAttributes() {
        return numOfAttributes;
    }

}
