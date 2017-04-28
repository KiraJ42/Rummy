import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class rummyGUI extends JDesktopPane {

    static JRadioButtonMenuItem gold;
    static JRadioButtonMenuItem red;
    static JRadioButtonMenuItem blue;

    static JFrame origin;
    public rummyGUI(JFrame origin2) {

        origin = origin2;
        setBackground( new Color(130,50,40) );

        setLayout( new BorderLayout(3,3) );

        JMenuBar mb = new JMenuBar();
        JMenuItem newG, rules;

        JMenu menu, set, cds;

        menu = new JMenu("Game");
        newG = new JMenuItem("New Game");

        set = new JMenu("Settings");
        rules = new JMenuItem("Rules");
       /* rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRules();
            }
        });*/
        cds = new JMenu("Cards");

        gold = new JRadioButtonMenuItem("Gold");
        red =  new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");

        radioHandler handle = new radioHandler();
        gold.addItemListener(handle);
        red.addItemListener(handle);
        blue.addItemListener(handle);

        ButtonGroup group = new ButtonGroup();
        group.add(gold);
        group.add(red);
        group.add(blue);
        gold.setSelected(true);

        set.add(rules);
        set.add(cds);

        cds.add(gold);
        cds.add(red);
        cds.add(blue);

        menu.add(newG);
        menu.add(set);

        origin.setJMenuBar(mb);
        mb.add(menu);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width*1, (screenSize.height*1)-100));



    }// end constructor

    private class radioHandler implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            if(e.getSource() == gold)
                Card.changeBack("gold");
            if(e.getSource() == red)
                Card.changeBack("red");
            if(e.getSource() == blue)
                Card.changeBack("blue");

            origin.validate();
            origin.repaint();
        }
    }

    /*protected void openRules() {

        //this is the JDesktopPane that goes with the window JFrame
        MyInternalFrame f = new MyInternalFrame();
        f.setVisible(true);
        this.add(f);


    }

    class MyInternalFrame extends JInternalFrame{
        public MyInternalFrame(){
            super("Frame", true, true, true, true);
            setSize(300, 300);
            setLocation(100, 100);
            setBackground(Color.WHITE);
        }
    }*/
}
