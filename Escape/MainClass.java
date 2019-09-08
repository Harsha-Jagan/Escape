/*
 *  Name    : Jagannathan Harshavardhan
 *  User ID : hxj4805
 *  Lab #   : Escape
 */

package Escape;

import javax.swing.JFrame;
public class MainClass
{
   public static void main(String args[])
   {
        int sizeGame[] = {700,400};
        GameClass jeroo = new GameClass();

        JFrame frame = new JFrame();
        frame.setSize(sizeGame[0],sizeGame[1]);
        frame.add(jeroo);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}