import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private typeBtn type;
    private Image pawnImg;

    public MyButton()
    {

    }

    public void setType(typeBtn type)
    {
        this.type = type;
        loadImage();
    }
    public typeBtn getType()
    {
        return this.type;
    }

    private void loadImage()
    {
        if(this.type == typeBtn.empty)
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

enum typeBtn
{
    pawnWhite(0),
    pawnBlack(1),
    block(2),
    empty(3);
    private  int unit;
    typeBtn(int unit) {
        this.unit = unit;

    }
    public int getUnit(){
        return this.unit;
    }
}