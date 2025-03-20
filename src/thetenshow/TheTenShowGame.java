package thetenshow;

import ca.sheridancollege.project.Deck;
import ca.sheridancollege.project.Game;
import java.util.ArrayList;
import java.util.List;

public class TheTenShowGame extends Game {
    private Deck deck;
    private List<TheTenShowPlayer> players;
    private TheTenShowCard hiddenCard; // (SIR card)
    private boolean sirRevealed = false; 

    public TheTenShowGame(String name) {
        super(name);
        this.deck = new Deck(52); // beacuse 1 card is hidden 
        this.players = new ArrayList<>();
    }

    public void addPlayer(TheTenShowPlayer player) {
        players.add(player);
    }

    
    public void hideRandomCard() {
        if (deck.getCards().size() > 0) {
            hiddenCard = (TheTenShowCard) deck.getCards().remove(0); // random SIR card
            System.out.println("One card is hidden (SIR).");
        } else {
            System.out.println("Deck is empty, cannot hide a card.");
        }
    }

    public void dealCards() {
        deck.shuffle(); 
        hideRandomCard(); 

       
        TheTenShowPlayer firstPlayer = players.get(0); 
        for (int i = 0; i < 12; i++) {
            firstPlayer.addCard((TheTenShowCard) deck.getCards().remove(0));
        }

        
        for (int i = 1; i < players.size(); i++) {
            TheTenShowPlayer player = players.get(i);
            for (int j = 0; j < 13; j++) {
                player.addCard((TheTenShowCard) deck.getCards().remove(0));
            }
        }
        System.out.println("Cards have been dealt.");
    }

    // Reveal SIR card if requested by a player
    public void revealSIRCard() {
        if (hiddenCard == null) {
            System.out.println("No hidden card to reveal.");
            return;
        }
        if (!sirRevealed) { 
            System.out.println("SIR Card is revealed: " + hiddenCard);
            sirRevealed = true; 
        } else {
            System.out.println("SIR Card has already been revealed.");
        }
    }

    @Override
    public void play() {
        dealCards(); 

        
        for (TheTenShowPlayer player : players) {
            System.out.println(player);
        }
    }

    @Override
    public void declareWinner() {
       
    }
}
