import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Card {

    public static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker"};
    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs", "Joker"};

    public String suit;
    public String rank;
    public int intRank;
    public int intSuit;

    public String front;
    static public String back = "Resources/images/gold_crown.png";
    public ImageIcon frontImg;
    static public ImageIcon backImg = new ImageIcon(back);
    private int x;
    private int y;
    
    private boolean up;
    
    Card(int s, int r, int iRank)
    {
        rank = RANKS[r];
        suit = SUITS[s];
        intRank = iRank;
        intSuit = s;
       backImg = null;
       frontImg = null;
        front = "Resources/images/" + rank + suit + ".png";
    }

    public static ImageIcon getImage(String path){
        try {
            InputStream is = Card.class.getResourceAsStream(back);
            Image image = ImageIO.read(is);
           Image img = image.getScaledInstance(140,200, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }catch(Exception e){
            System.out.println("File not found");
        }
        return new ImageIcon();
    }

   static public void changeBack(String p){
        String path = "Resources/images/gold_crown.png";

        if(p.equals("gold"))
            path = "Resources/images/gold_crown.png";
        if(p.equals("red"))
            path = "Resources/images/card back red.png";
        if(p.equals("blue"))
            path = "Resources/images/blue.png";
        back = path;

       try {
           InputStream is = new BufferedInputStream(new FileInputStream(back));
           Image image = ImageIO.read(is);
           Image img = image.getScaledInstance(140,200, Image.SCALE_SMOOTH);
           backImg = new ImageIcon(img);
       }catch(Exception e){
           System.out.println("File not found");
       }
       backImg = new ImageIcon();
    }

    public void makeImage(){
        try {
            InputStream is = this.getClass().getResourceAsStream(front);
            Image image = ImageIO.read(is);
            Image img = image.getScaledInstance(140,200, Image.SCALE_SMOOTH);
            frontImg = new ImageIcon(img);
        }catch(Exception e){
            System.out.println("File not found");
        }
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
    
    public int getIntSuit()
    {
        return intSuit;
    }
    
    public int getIntRank()
    {
        return intRank;
    }
        
    
    
    public int getValue(){
       if(rank.equals("A"))
           return 20;
       else if(rank.equals("Q") || rank.equals("K") || rank.equals("J") || rank.equals("10"))
           return 10;
       else if(rank.equals("Joker"))
          return 50;
        else
            return 5;
     }
    
    
    public void draw(Graphics g, Component c)
    {
        if(up)
            frontImg.paintIcon(c, g, x, y);
        else
            backImg.paintIcon(c, g, x, y);
    }
    
    public int getHeight(){return frontImg.getIconHeight();}
    public int getWidth(){return frontImg.getIconWidth();}
    
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

