import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphicBoardPvP extends AbstractGraphicBoard {
    public GraphicBoardPvP() {
        super();
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));  // Add this line
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSquares();
        paintUnits();
        showPossibleMoves();
    }

    public void paintSquares() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.graphicBoard[i][j] = new MyButton();
                this.graphicBoard[i][j].addActionListener(new AL(new Point(i,j)));
                this.graphicBoard[i][j].setBackground(new Color(212, 228, 252));
                this.graphicBoard[i][j].setEnabled(false);
                add(this.graphicBoard[i][j]);
            }
        }
    }

    public void paintUnits() {
        Point[] ppArr = dataBoard.getPlayersPos();
        ArrayList<Point> bArr = dataBoard.getBlockArr();
        for (int i = 0; i < ppArr.length; i++) {
            MyButton btn = this.graphicBoard[ppArr[i].y][ppArr[i].x];
            btn.setType(dataBoard.getTypeByValue(i));
            btn.setEnabled( btn.getType().getUnit() == LogicBoard.turn || LogicBoard.turn == 2 && i == 0);
        }

        for (Point block : bArr) {
            this.graphicBoard[block.y][block.x].setType(dataBoard.getTypeByValue(2));
        }
    }

    public void showPossibleMoves() {
        if (LogicBoard.turn < 2) {
            Point playerPos = dataBoard.getPlayersPos()[LogicBoard.turn];
            int r = playerPos.y;
            int c = playerPos.x;

            for (int i = Math.max(0, r - 1); i <= Math.min(BOARD_SIZE - 1, r + 1); i++) {
                for (int j = Math.max(0, c - 1); j <= Math.min(BOARD_SIZE - 1, c + 1); j++) {
                    if (i != r || j != c && !dataBoard.isContainsInBlockArr(i, j)) {
                        this.graphicBoard[i][j].setBackground(Color.green);
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
           LogicBoard.changeTurn();


        }
    }
}
