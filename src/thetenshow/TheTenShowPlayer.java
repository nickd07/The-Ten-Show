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

    public void clearHand() {
        hand.clear();
    }

    @Override
    public void play() {
        // Not used directly for now
    }

    @Override
    public String toString() {
        return getName() + "'s hand: " + hand;
    }
}
