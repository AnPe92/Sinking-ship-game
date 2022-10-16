import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        GameBoard gameBoard = new GameBoard(10);
        Game game = new Game();
        game.game(true, gameBoard);

//        Game test = new Game();
//        test.game(running);
    }
}
