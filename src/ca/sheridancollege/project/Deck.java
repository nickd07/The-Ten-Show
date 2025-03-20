package ca.sheridancollege.project;

import thetenshow.TheTenShowCard;

public class Deck extends GroupOfCards {

    public Deck(int size) {
        super(size);
        populateDeck(); 
    }

    // Populate the deck with cards
    public void populateDeck() {
        if (getCards().isEmpty()) { 
            for (Suit suit : Suit.values()) {
                for (int i = 2; i <= 14; i++) { // 2-10, J=11, Q=12, K=13, A=14
                    getCards().add(new TheTenShowCard(suit, i));
                }
            }
        }
    }
}
