package thetenshow;


import java.util.*;

class Deck {
    private List<Card> card;

    public Deck() {
        card = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 14; i++) { // 2-10, J=11, Q=12, K=13, A=14
                card.add(new Card(suit, i));
            }
        }
        Collections.shuffle(card);
    }

    public Card drawCard() {
        return card.isEmpty() ? null : card.remove(0);
    }
}
    
