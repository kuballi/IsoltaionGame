import java.util.ArrayList;
import java.awt.Point;

public class LogicBoard implements BoardParametersIE {
    protected short[][] board;
    protected ArrayList<Point> blockArr;
    protected Point[] playersPos; // white is [0], black is [1].
    public static boolean turn = true; // ture - white, false - black

    public LogicBoard() {

    }

    public void initBoard() {
        this.board = new short[BOARD_SIZE][BOARD_SIZE];
        this.blockArr = new ArrayList<Point>();
        this.playersPos = new Point[2];
        board[0][BOARD_SIZE / 2] = 1;
        board[BOARD_SIZE - 1][BOARD_SIZE / 2] = 2;
        playersPos[0] = new Point(0,BOARD_SIZE / 2);
        playersPos[1] = new Point(BOARD_SIZE - 1,BOARD_SIZE / 2);
    }

    public ArrayList<Point> getBlockArr() {
        return blockArr;
    }

    public Point[] getPlayersPos() {
        return playersPos;
    }
    public typeBtn getTypeByValue(int unit){
        switch(unit){
            case 0:
                return typeBtn.pawnWhite;
            case 1:
                return typeBtn.pawnBlack;
            default:
                return typeBtn.block;
        }
    }
    public static void changeTurn()
    {
        LogicBoard.turn = !LogicBoard.turn;
    }

    public void makeMove()
    {

    }
}
