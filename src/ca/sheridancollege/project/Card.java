package thetenshow;


public class Card {
    private int value;  // 2 to 14
    private Suit suit;  // Suit (HEARTS, DIAMONDS, CLUBS, SPADES)

    // Constructor with Suit and value as arguments
    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String cardValue;
        switch (value) {
            case 11:
                cardValue = "Jack";
                break;
            case 12:
                cardValue = "Queen";
                break;
            case 13:
                cardValue = "King";
                break;
            case 14:
                cardValue = "Ace";
                break;
            default:
                cardValue = String.valueOf(value); // For numbers 2-10
        }
        return cardValue + " of " + suit;
    }
}
