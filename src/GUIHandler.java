import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GUIHandler {
    public GUIHandler() {
        System.out.println("New gui handler initialized");
        setupGui();
    }

    private void setupGui (){
        System.out.println("Setting up gui");
        JFrame frame = new JFrame("Word Hunt");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel testPanel = new JPanel();
        JLabel testText = new JLabel("Hello World");

        JPanel testPanel2 = new JPanel();
        JButton testButton = new JButton("Click me!");

        testPanel2.add(testButton);
        testPanel.add(testText);
        // the panel is not visible in output
        frame.add(testPanel, BorderLayout.NORTH);
        frame.add(testButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}