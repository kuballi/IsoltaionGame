import java.util.ArrayList;
import java.awt.Point;

public class LogicBoard implements BoardParametersIE {
    protected short[][] board; // 0 - empty, 1 - white, 2 - black, -1 - block
    protected ArrayList<Point> blockArr;
    protected Point[] playersPos; // white is [0], black is [1].
    public static short turn = 2; // 0 - white, 1 - black, 2- noOne

    public LogicBoard() {

    }

    public void initBoard() {
        this.board = new short[BOARD_SIZE][BOARD_SIZE];
        this.blockArr = new ArrayList<Point>();
        this.playersPos = new Point[2];
        this.board[BOARD_SIZE / 2][0] = 1;
        this.board[BOARD_SIZE / 2][BOARD_SIZE - 1] = 2;
        this.playersPos[0] = new Point(BOARD_SIZE / 2,0);
        this.playersPos[1] = new Point(BOARD_SIZE / 2,BOARD_SIZE - 1);
    }

    public boolean isContainsInBlockArr(int x, int y) {
        return this.blockArr.contains(new Point(x,y));
    }
    public ArrayList<Point> getBlockArr() {
        return this.blockArr;
    }

    public Point[] getPlayersPos() {
        return this.playersPos;
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
        LogicBoard.turn = (short) (LogicBoard.turn == 2 ? 0 : LogicBoard.turn | 1);
    }
    public boolean isValidMove(Point from, Point to) {
        // Check if the destination cell is within the bounds of the board
        if (to.x < 0 || to.x >= BOARD_SIZE || to.y < 0 || to.y >= BOARD_SIZE) {
            return false; // Destination cell is out of bounds
        }

        // Check if the destination cell is not blocked
        if (this.board[to.y][to.x] == -1) {
            return false; // Destination cell is blocked
        }
        int r = from.y;
        int c = from.x;
        for (int i = Math.max(0, r - 1); i <= Math.min(BOARD_SIZE - 1, r + 1); i++) {
            for (int j = Math.max(0, c - 1); j <= Math.min(BOARD_SIZE - 1, c + 1); j++) {
                if(i == to.y && j == to.x)
                    return true; // Move is valid

            }
        }


        return true; // Move is valid
    }

    public void makeMove(Move move) {

        Point to = move.getCurrPoint();
        Point from = move.getPrevPoint();
        if(to == null || from == null)
        {
            changeTurn();
            return;
        }

        if (isValidMove(from,to)) {
            this.board[to.x][to.y] = this.board[from.x][from.y];
            this.board[from.x][from.y] = 0;

            // Update player's position
            this.playersPos[this.turn] = to;

            // Change the turn
            changeTurn();
        }
    }
}