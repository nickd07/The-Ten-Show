package thetenshow;


import java.util.*;



class Game {
    private List<Player> player;
    private Deck deck;

    public Game() {
        player = new ArrayList<>();
        deck = new Deck();
    }

    public void addPlayer(String name) {
        player.add(new Player(name));
    }

    public void dealCards() {
        for (int i = 0; i < 13; i++) {
            for (Player player : player) {
                player.addCard(deck.drawCard());
            }
        }
        System.out.println("Cards have been dealt.");
    }

    public void startGame() {
        dealCards();
        for (Player player : player) {
            System.out.println(player);
        }
    }
}
