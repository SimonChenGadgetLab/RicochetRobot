package robot;

import javax.swing.JFrame;


public class Ricochet {

    public static void main(String[] args) {
        JFrame boardFrame = new JFrame("Board");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.getContentPane().add(new Board());
        boardFrame.pack();
        boardFrame.setVisible(true);
    }
    
}
