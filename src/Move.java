import java.awt.*;

public class Move {
    private Point prevPoint;
    private Point currPoint;

    public Move(Point prevPoint, Point currPoint) {
        this.currPoint = currPoint;
        this.prevPoint = prevPoint;
    }

    public Point getCurrPoint() {
        return this.currPoint;
    }

    public int isFullMove() { //  0 if the move is EMPTY, 1 if the move half full, 2 if the move is full.
        if (this.prevPoint == null && this.currPoint == null) return 0;
        else if (this.prevPoint == null || this.currPoint == null) return 1;
        return 2;
    }

    public void setCurrPoint(Point currPoint) {
        this.currPoint = currPoint;
    }

    public Point getPrevPoint() {
        return this.prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }
}
