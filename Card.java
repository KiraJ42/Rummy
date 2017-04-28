import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * Created by Owner on 3/28/2017.
 */
public class Card {

    
    //static String bPath = "card back red.png";
    //URL bURL = cldr.getResource(bPath);
    //public static final ImageIcon Cardback =  new ImageIcon(bPath);
    
    //card ranks and
    public static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Joker"};
    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs", "Joker"};

    public String suit;
    public String rank;
    public int intRank;
    
    public ImageIcon front;
    static public ImageIcon back;
    private int x;
    private int y;
    
    private boolean up;
    
    Card(int s, int r, int iRank)
    {
        rank = RANKS[r];
        suit = SUITS[s];
        intRank = iRank;
        //front = f;

        back = getImage("images/gold_crown.png");
        front = getImage("images/" + rank + suit + ".png");
    }

    public ImageIcon getImage(String path){
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(path));
            Image image = ImageIO.read(is);
           Image img = image.getScaledInstance(140,200, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }catch(Exception e){
            System.out.println("File not found");
        }
        return new ImageIcon();
    }

   static public void changeBack(String p){
        String path = "images/gold_crown.png";

        if(p.equals("gold"))
            path = "images/gold_crown.png";
        if(p.equals("red"))
            path = "images/card back red.png";
        if(p.equals("blue"))
            path = "images/blue.png";

       try {
           InputStream is = new BufferedInputStream(new FileInputStream(path));
           Image image = ImageIO.read(is);
           Image img = image.getScaledInstance(140,200, Image.SCALE_SMOOTH);
           back = new ImageIcon(img);
       }catch(Exception e){
           System.out.println("File not found");
       }
       back = new ImageIcon();
    }

    public String toString()
    {
        if(suit.equals("Joker") && rank.equals("Joker"))
            return "Joker";
        return rank + " of " + suit;
    }

    public String getRank()
    {
        return rank;
    }

    public String getSuit()
    {
        return suit;
    }
    
    public int getIntRank()
    {
        return intRank;
    }
        
    
    
    public int getValue(){
       if(rank.equals("Ace"))
           return 20;
       else if(rank.equals("Queen") || rank.equals("King") || rank.equals("Jack") || rank.equals("10"))
           return 10;
       else if(rank.equals("Joker"))
          return 50;
        else
            return 5;
     }
    
    
    public void draw(Graphics g, Component c) 
    {
        if(up)
            front.paintIcon(c, g, x, y);
        else
            back.paintIcon(c, g, x, y);
    }
    
    public int getHeight(){return front.getIconHeight();}
    public int getWidth(){return front.getIconWidth();}
    
    public boolean contains(int a, int b)
    {
        return ( a > x && a < (x + getWidth()) && b > y && b < (y + getHeight()));
    }
    
    public int getY(){return y;}     
    public int getX(){return x;}
    
    public void moveTo(int a, int b)
    {
        x = a;
        y = b;
    }
}

