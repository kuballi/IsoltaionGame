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


    public void setCurrPoint(Point currPoint) {
        this.currPoint = currPoint;
    }

    public Point getPrevPoint() {
        return this.prevPoint ;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }
}
