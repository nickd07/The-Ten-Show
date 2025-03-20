package thetenshow;

import ca.sheridancollege.project.Player;
import java.util.ArrayList;
import java.util.List;

public class TheTenShowPlayer extends Player {
    private List<TheTenShowCard> hand;

    public TheTenShowPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    public void addCard(TheTenShowCard card) {
        hand.add(card);
    }

    public List<TheTenShowCard> getHand() {
        return hand;
    }

    @Override
    public void play() {
        // Add the logic for playing the card
    }

    @Override
    public String toString() {
        return getName() + "'s hand: " + hand;
    }
}
