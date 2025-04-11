package ca.sheridancollege.project;

import thetenshow.TheTenShowGame;
import thetenshow.TheTenShowPlayer;

public class TheTenShow {
    public static void main(String[] args) {
        TheTenShowGame game = new TheTenShowGame("The Ten Show");

        game.addPlayer(new TheTenShowPlayer("Player 1"));
        game.addPlayer(new TheTenShowPlayer("Player 2"));
        game.addPlayer(new TheTenShowPlayer("Player 3"));
        game.addPlayer(new TheTenShowPlayer("Player 4"));

        game.play(); 
    }
}
