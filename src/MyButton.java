import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private TypeUnit type;
    private Image pawnImg;

    public MyButton()
    {

    }

    public void setType(TypeUnit type)
    {
        this.type = type;
        loadImage();
    }
    public TypeUnit getType()
    {
        return this.type;
    }

    private void loadImage()
    {
        if(this.type == TypeUnit.EMPTY)
        {
            pawnImg = null;
            return;
        }
        ImageIcon icon = new ImageIcon(getClass().getResource( type.toString() + ".png"));
        pawnImg = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(pawnImg, getWidth() / 2 - 20, getHeight() - 48, 35, 40, this);
    }
}

enum TypeUnit
{
    PAWN_WHITE(0),
    PAWN_BLACK(1),
    block(2),
    EMPTY(3);
    private  int unit;

    TypeUnit(int i) {
        this.unit = i;
    }

    public int getUnit(){
        return this.unit;
    }
    public  boolean isPawn(){return this.unit == 1 || this.unit == 0;}
}