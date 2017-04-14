import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class rummyGUI extends JPanel {
    public rummyGUI() {

        setBackground( new Color(130,50,40) );

        setLayout( new BorderLayout(3,3) );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( new Color(220,200,180) );
        add(buttonPanel, BorderLayout.SOUTH);

        JButton higher = new JButton( "Start Game" );
        buttonPanel.add(higher);

        JButton lower = new JButton( "New Game" );
        buttonPanel.add(lower);

        JButton settings = new JButton( "Settings" );
        buttonPanel.add(settings);

        setBorder(BorderFactory.createLineBorder( new Color(130,50,40), 3) );

    }  // end constructor
}
