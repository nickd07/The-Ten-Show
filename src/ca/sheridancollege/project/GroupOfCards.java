package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private ArrayList<Card> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>(); // Initialize the list of cards
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards); // Ensure cards list is not null
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
