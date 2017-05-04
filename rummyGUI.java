
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class rummyGUI extends JDesktopPane {

    static JRadioButtonMenuItem gold;
    static JRadioButtonMenuItem red;
    static JRadioButtonMenuItem blue;
    static JRadioButtonMenuItem alpaca;
    static JRadioButtonMenuItem FSU;

    private JMenuItem Rummy;

    static Game game;
    static JFrame origin;

    public rummyGUI(JFrame origin2, Game g) {

        origin = origin2;
        game = g;

        setBackground( new Color(130,50,40) );

        setLayout( new BorderLayout(3,3) );

        JMenuBar mb = new JMenuBar();

        JMenu set, cds;

        set = new JMenu("Settings");

        Rummy = new JMenuItem("Rules");

        ruleHandler ruleH = new ruleHandler();
        Rummy.addActionListener(ruleH);

        cds = new JMenu("Cards");

        gold = new JRadioButtonMenuItem("Gold");
        red =  new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");
        alpaca = new JRadioButtonMenuItem("Alpaca");
        FSU = new JRadioButtonMenuItem("FSU");

        radioHandler handle = new radioHandler();
        gold.addItemListener(handle);
        red.addItemListener(handle);
        blue.addItemListener(handle);
        alpaca.addItemListener(handle);
        FSU.addItemListener(handle);

        ButtonGroup group = new ButtonGroup();
        group.add(gold);
        group.add(red);
        group.add(blue);
        group.add(alpaca);
        group.add(FSU);
        gold.setSelected(true);

        set.add(Rummy);
        set.add(cds);

        cds.add(gold);
        cds.add(red);
        cds.add(blue);
        cds.add(alpaca);
        cds.add(FSU);

        origin.setJMenuBar(mb);
        mb.add(set);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(screenSize.width*1, (screenSize.height*1)-100));



    }// end constructor

    private class radioHandler implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            if(e.getSource() == gold)
                Card.backImg = Card.getImage("Resources/images/gold_crown.png");
            if(e.getSource() == red)
                Card.backImg = Card.getImage("Resources/images/card back red.png");
            if(e.getSource() == blue)
                Card.backImg = Card.getImage("Resources/images/blue.png");
            if(e.getSource() == alpaca)
                Card.backImg = Card.getImage("Resources/images/alpaca.png");
            if(e.getSource() == FSU)
                Card.backImg = Card.getImage("Resources/images/FSU.png");

            game.centerCard.remove(game.cardDeck);
            JLabel temp = game.discard;
            game.centerCard.remove(game.discard);

            game.discard = temp;
            game.discard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (game.drawn || game.pick > 0) {
                        JOptionPane.showMessageDialog(game.window, "You've already drawn this turn");
                        game.drawn = false;
                    }
                    else {
                        Card cd = game.d.takeDiscard();
                        game.player.hand.add(cd);
                        game.playerHand.add(cd);
                        game.updateDiscard();
                        game.drawn = true;
                        game.window.validate();
                        game.window.repaint();
                    }
                }
            });

            temp = new JLabel(Card.backImg);
            temp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("why here");
                    game.pickCard();
                }
            });
            game.cardDeck = temp;
            game.centerCard.add(game.cardDeck);
            game.centerCard.add(game.discard);
            origin.validate();
            origin.repaint();
        }
    }

    protected void openRules(String input) {

        String path = "Rummy";
        JDialog dialog = new JDialog();
        JTextArea rulesText = new JTextArea();
        FileReader reader = null;
        try {
            String filename = "Resources/Rules/"+path+".txt";
            reader = new FileReader(filename);
            rulesText.read(reader, filename);

        }catch (IOException ie){
            //put something here
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    //something here too
                }
            }
        }

        JScrollPane scroll = new JScrollPane(rulesText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        dialog.setTitle(path + " Rules");
        dialog.add(scroll);
        dialog.setSize(new Dimension((screenSize.width/2) + 100, (screenSize.height*1)-50 ));
        dialog.setLocation(100, 10);
        dialog.setVisible(true);

    }

    private class ruleHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                openRules("Rummy");
        }
    }
}
