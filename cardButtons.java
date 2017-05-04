//import sun.plugin2.message.transport.SerializingTransport;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

public class PlayerInfo extends JPanel {

    protected JLabel SCORE;
    protected JLabel SETS;
    protected JLabel SERIES;
    protected JLabel H;
    protected JLabel D;
    protected JLabel S;
    protected JLabel C;
    protected Player player;
    protected Font bigger;

    public PlayerInfo(Player p){
        setBackground(new Color (250, 250, 210));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);

        player = p;
        setLayout(new GridLayout(0,1));
        JLabel name = new JLabel(" Player Name: " + p.name + " ");
        Font font = name.getFont();
        bigger = new Font(font.getFontName(), Font.PLAIN, 16);
        name.setFont(bigger);
        SCORE = new JLabel(" Score: " + p.getTotalScore() + " ");
        SCORE.setFont(bigger);
        SETS = new JLabel(" Sets: " +  " ");
        SETS.setFont(bigger);
        SERIES = new JLabel(" Series: " + " ");
        SERIES.setFont(bigger);
        H = new JLabel("\u2665: " + " ");
        H.setFont(bigger);
        D = new JLabel("\u2666: " + " ");
        D.setFont(bigger);
        S = new JLabel("\u2660: " + " ");
        S.setFont(bigger);
        C = new JLabel("\u2663: " + " ");
        C.setFont(bigger);

        add(name);
        add(SCORE);
        add(SETS);
        add(SERIES);
        add(H);
        add(D);
        add(S);
        add(C);
    }

    public void updateScore(Player p){
        //System.out.println(p.getTotalScore());
        this.remove(SCORE);
        this.remove(SERIES);
        this.remove(SETS);
        this.remove(H);
        this.remove(D);
        this.remove(S);
        this.remove(C);

        SCORE = new JLabel(" Score: " + p.getTotalScore() + " ");
        SCORE.setFont(bigger);
        //String series = "";
        String sets = "";
        String hearts = "";
        String diamonds = "";
        String clubs = "";
        String spades = "";
        for(Lays x : p.ScoredLays){
            if(x instanceof Set){
                sets = sets + x.toString();
            }
            else if(x instanceof Series){
                //series = series + x.toString();
                if(x.lay.get(x.lay.size()-1).getSuit().equals("Hearts"))
                    hearts = hearts + x;
                else if(x.lay.get(x.lay.size()-1).getSuit().equals("Diamonds"))
                    diamonds = diamonds + x;
                else if(x.lay.get(x.lay.size()-1).getSuit().equals("Spades"))
                    spades = spades + x;
                else
                    clubs = clubs + x;

            }
        }
        SETS = new JLabel(" Sets: " +  sets + " ");
        SETS.setFont(bigger);
        SERIES = new JLabel(" Series: " + " ");
        SERIES.setFont(bigger);
        H = new JLabel("\u2665: " + hearts);
        H.setFont(bigger);
        D = new JLabel("\u2666: " + diamonds);
        D.setFont(bigger);
        S = new JLabel("\u2660: " + spades);
        S.setFont(bigger);
        C = new JLabel("\u2663: " + clubs);
        C.setFont(bigger);

        add(SCORE);
        add(SETS);
        add(SERIES);
        add(H);
        add(D);
        add(S);
        add(C);
        this.validate();
        this.repaint();
    }



}
