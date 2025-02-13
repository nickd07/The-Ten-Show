package thetenshow;

public class TheTenShow{
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer("Nick"); 
        game.addPlayer("Urva"); 
        game.addPlayer("Utam");
        game.addPlayer("Hardik");
        game.startGame();
    }
}

