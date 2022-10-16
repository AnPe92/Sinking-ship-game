import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GameBoard {

    private  HashMap <int[], Tile> gameBoard = new HashMap <>();

    public HashMap<int[], Tile> getGameBoard() {
        return gameBoard;
    }

    public GameBoard(int boardSize){
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Tile tile = new Tile(false, false, false, false);
                this.gameBoard.put(new int[]{i, j}, tile);
            }
        }



    }

    @Override
    public String toString() {
        return "GameBoard{" +
                ", gameBoard=" + gameBoard +
                '}';
    }
}
