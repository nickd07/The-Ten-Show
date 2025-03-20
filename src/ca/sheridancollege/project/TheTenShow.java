package ca.sheridancollege.project;

import thetenshow.TheTenShowGame;
import thetenshow.TheTenShowPlayer;

public class TheTenShow {
    public static void main(String[] args) {
        TheTenShowGame game = new TheTenShowGame("Game of Ten's");
        game.addPlayer(new TheTenShowPlayer("Nick"));
        game.addPlayer(new TheTenShowPlayer("Urva"));
        game.addPlayer(new TheTenShowPlayer("Utam"));
        game.addPlayer(new TheTenShowPlayer("Hardik"));
        game.play();
    }
}
