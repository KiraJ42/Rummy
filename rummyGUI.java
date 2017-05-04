
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class rummyGUI extends JDesktopPane {

    private JRadioButtonMenuItem gold;
    private JRadioButtonMenuItem red;
    private JRadioButtonMenuItem blue;
    private JRadioButtonMenuItem alpaca;

    private JMenuItem Gin;
    private JMenuItem Rummy;

    static Game game;
    static JFrame origin;

    public rummyGUI(JFrame origin2, Game g) {

        origin = origin2;
        game = g;

        setBackground( new Color(130,50,40) );

        setLayout( new BorderLayout(3,3) );

        JMenuBar mb = new JMenuBar();
        JMenuItem newG;

        JMenu menu, set, cds, rules;

        menu = new JMenu("Game");
        newG = new JMenuItem("New Game");

        gameHandler newGame = new gameHandler();
        newG.addActionListener(newGame);

        set = new JMenu("Settings");

        rules = new JMenu("Rules");

        Gin = new JMenuItem("Gin");
        Rummy = new JMenuItem("Rummy");

        ruleHandler ruleH = new ruleHandler();
        Gin.addActionListener(ruleH);
        Rummy.addActionListener(ruleH);
        rules.add(Gin);
        rules.add(Rummy);

        cds = new JMenu("Cards");

        gold = new JRadioButtonMenuItem("Gold");
        red =  new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");
        alpaca = new JRadioButtonMenuItem("Alpaca");

        radioHandler handle = new radioHandler();
        gold.addItemListener(handle);
        red.addItemListener(handle);
        blue.addItemListener(handle);
        alpaca.addItemListener(handle);

        ButtonGroup group = new ButtonGroup();
        group.add(gold);
        group.add(red);
        group.add(blue);
        group.add(alpaca);
        gold.setSelected(true);

        set.add(rules);
        set.add(cds);

        cds.add(gold);
        cds.add(red);
        cds.add(blue);
        cds.add(alpaca);

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
                Card.backImg = Card.getImage("images/gold_crown.png");
            if(e.getSource() == red)
                Card.backImg = Card.getImage("images/card back red.png");
            if(e.getSource() == blue)
                Card.backImg = Card.getImage("images/blue.png");
            if(e.getSource() == alpaca)
                Card.backImg = Card.getImage("images/alpaca.png");

            game.centerCard.remove(game.cardDeck);
            game.cardDeck = new JLabel(Card.backImg);
            game.cardDeck.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game.pickCard();
                }
            });
            game.centerCard.add(game.cardDeck);
            origin.validate();
            origin.repaint();
        }
    }

    protected void openRules(String input) {

        String path = "Error";
        if(input.equals("Rummy"))
            path = "Rummy";
        if(input.equals("Gin Rummy"))
            path = "Gin Rummy";
        JDialog dialog = new JDialog();
        JTextArea rulesText = new JTextArea();
        FileReader reader = null;
        try {
            String filename = "Rules/"+path+".txt";
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
            if(e.getSource() == Gin)
                openRules("Gin Rummy");
            if(e.getSource() == Rummy)
                openRules("Rummy");
        }
    }

    private class gameHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(Player p : game.players){
                p.clear(game.d);
                p.totalScore = 0;
                game.d.Deal(7, game.players);
            }
            game.window.validate();
            game.window.repaint();
        }
    }
}
