/*
 *  Name    : Jagannathan Harshavardhan
 *  User ID : hxj4805
 *  Lab #   : Escape
 */

package Escape;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameClass extends JPanel implements ActionListener, KeyListener
{
    private int delay = 15;
    Timer timer = new Timer(delay,this);
//sizes    
    private int mazeSize[] = {300,700};
    private int heroSize = 80;
//heroPos    
    private int heroCol = 20;
    private int heroRow = 210;
//walls
    ArrayList<Rectangle2D> walls = new ArrayList<Rectangle2D>();
    private int speed = 10;
    private int spacer = 0;
    private int counter = 0;
//scores
    private int score = 0;
    
    public GameClass()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setFont(new Font("Monotype Corsiva", Font.PLAIN, 36)); 
        g.drawString(" Score :: "+score/2, 0, 340);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.drawLine(0, mazeSize[0]/3, mazeSize[1], mazeSize[0]/3);
        g2.drawLine(0, mazeSize[0]*2/3, mazeSize[1], mazeSize[0]*2/3);
        g2.drawLine(0, mazeSize[0], mazeSize[1], mazeSize[0]);
        
        Rectangle2D hero = new Rectangle2D.Double(heroCol,heroRow,heroSize,heroSize);
        g2.fill(hero);
        
        counter++;
        if(counter==250)
        {
            if(delay>1)
            {
                delay = delay - 1;
                timer.setDelay(delay);
            }
            counter = 0;
        }
        
        for(int a=0; a<walls.size(); a++)
        {
            if(hero.intersects(walls.get(a)))
            {
                sleep(1);
                System.out.println("Game over. You lost. Your score :: "+score/2);
                System.exit(0);
            }
            if(heroCol==walls.get(a).getX())
                score++;
        }
        
        mover(g2);
        spacer++;
        if(spacer>100)
        {
            spacer = 0;
            int dice = (int)(Math.random()*3)+1;
            if(dice==1)
            {
                Rectangle2D wallB = new Rectangle2D.Double(mazeSize[1],mazeSize[0]/3,20,100);
                g2.fill(wallB);
                walls.add(wallB);

                Rectangle2D wallC = new Rectangle2D.Double(mazeSize[1],mazeSize[0]*2/3,20,100);
                g2.fill(wallC);
                walls.add(wallC);
            }
            else if(dice==2)
            {
                Rectangle2D wallA = new Rectangle2D.Double(mazeSize[1],0,20,100);
                g2.fill(wallA);
                walls.add(wallA);

                Rectangle2D wallC = new Rectangle2D.Double(mazeSize[1],mazeSize[0]*2/3,20,100);
                g2.fill(wallC);
                walls.add(wallC);
            }
            else
            {
                Rectangle2D wallA = new Rectangle2D.Double(mazeSize[1],0,20,100);
                g2.fill(wallA);
                walls.add(wallA);

                Rectangle2D wallB = new Rectangle2D.Double(mazeSize[1],mazeSize[0]/3,20,100);
                g2.fill(wallB);
                walls.add(wallB);
            }
        }
        timer.start();
    }
    
    public static void sleep(int time)
    {
        try
        {
            Thread.sleep(time*1000);
        }
        catch(Exception e) {}
    }
    
    public void mover(Graphics2D g2)
    {
        for(int a=0; a<walls.size(); a++)
        {
            Rectangle2D temp = new Rectangle2D.Double((int)walls.get(a).getX()-speed, (int)walls.get(a).getY(), (int)walls.get(a).getWidth(), (int)walls.get(a).getHeight());
            g2.fill(temp);
            walls.remove(a);
            walls.add(a,temp);
        }
        
        if(!walls.isEmpty() && walls.get(0).getX()<=0)
            walls.remove(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_DOWN && heroRow+100<mazeSize[0])
        {
            heroRow+=100;
        }
        if(keyCode == KeyEvent.VK_UP && heroRow-100>0)
        {
            heroRow-=100;
        }
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
}