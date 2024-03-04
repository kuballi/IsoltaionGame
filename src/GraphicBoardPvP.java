import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class GraphicBoardPvP extends AbstractGraphicBoard {
    public GraphicBoardPvP() {
        super();
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSquares();
        paintUnits();
        showPossibleMoves();

    }

    public void paintSquares() {
        boolean isEmpty = this.graphicBoard[0][0] == null;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(isEmpty)
                {
                    this.graphicBoard[i][j] = new MyButton();
                    this.graphicBoard[i][j].addActionListener(new AL(new Point(i, j)));

                    this.graphicBoard[i][j].setIcon(null);
                    add(this.graphicBoard[i][j]);
                }
                this.graphicBoard[i][j].setBackground(new Color(212, 228, 252));
                this.graphicBoard[i][j].setType(TypeUnit.EMPTY);
                this.graphicBoard[i][j].setEnabled(false);
            }
        }
    }
    public void paintUnits() {
        Point[] ppArr = dataBoard.getPlayersPos();
        ArrayList<Point> bArr = dataBoard.getBlockArr();
        for (int i = 0; i < ppArr.length; i++) {
            MyButton btn = this.graphicBoard[ppArr[i].x][ppArr[i].y];
            btn.setType(dataBoard.getTypeByValue(i));
            btn.setEnabled(btn.getType().getUnit() == LogicBoard.turn.getUnit() || LogicBoard.turn.getUnit() == 3 && i == 0);
        }

        for (Point block : bArr) {
            this.graphicBoard[block.x][block.y].setType(dataBoard.getTypeByValue(2));
            this.graphicBoard[block.x][block.y].setEnabled(false);
        }
    }

    public void showPossibleMoves() {

       if(LogicBoard.turn.isPawn() && move != null){
            Point playerPos = dataBoard.getPlayersPos()[LogicBoard.turn.getUnit()];
            int r = playerPos.x;
            int c = playerPos.y;
            this.graphicBoard[r][c].setEnabled(false);
            for (int i = Math.max(0, r - 1); i <= Math.min(BOARD_SIZE - 1, r + 1); i++) {
                for (int j = Math.max(0, c - 1); j <= Math.min(BOARD_SIZE - 1, c + 1); j++) {
                    if ((graphicBoard[i][j].getType() == TypeUnit.EMPTY) && (i != r || j != c)) {
                        this.graphicBoard[i][j].setBackground(Color.green);
                        this.graphicBoard[i][j].setEnabled(true);
                    }
                }
            }
        }
       else if(LogicBoard.isBlocking && move != null && move.isFullMove() == 2) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if ( graphicBoard[i][j].getType() == TypeUnit.EMPTY) {
                        this.graphicBoard[i][j].setBackground(Color.red);
                        this.graphicBoard[i][j].setEnabled(true);
                    }
                }
            }
        }
    }

    class AL implements ActionListener {
        Point point;
        public AL(Point point) {
            this.point = point;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MyButton btn = ((MyButton) e.getSource());
            btn.repaint();
            if(move == null)
                move = new Move(this.point,null);
            else
            {
                move.setCurrPoint(this.point);
               graphicBoard[move.getPrevPoint().x][move.getPrevPoint().y].repaint() ;

            }
            if(LogicBoard.isBlocking)
                dataBoard.makeBlock(point);
            else
                dataBoard.makeMove(move);
            if(move != null && move.isFullMove() == 2)
                move = null;
            repaint();
        }
    }
}
