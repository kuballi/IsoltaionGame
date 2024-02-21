import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public abstract class AbstractGraphicBoard extends JPanel implements  BoardParametersIE{
protected LogicBoard dataBoard;
protected MyButton[][] graphicBoard;
public AbstractGraphicBoard(){
    dataBoard = new LogicBoard();
    this.graphicBoard = new MyButton[BOARD_SIZE][BOARD_SIZE];
    dataBoard.initBoard();
}
public abstract void paintSquares();
public abstract void paintUnits();
//public abstract void showPossibleMoves();dasd
}
