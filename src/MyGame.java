import javax.swing.*;
import java.awt.*;

public class MyGame extends JFrame implements BoardParametersIE {
    public MyGame() {
        setTitle("Isolation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 420);
        setResizable(false);

        // Create an instance of GraphicBoardPvP (which is now a JPanel)
        GraphicBoardPvP graphicBoardPvP = new GraphicBoardPvP();

        // Add the GraphicBoardPvP panel to the content pane of the main JFrame
        add(graphicBoardPvP);

        // Make the frame visible
        setVisible(true);
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(MyGame::new);
    }
}
