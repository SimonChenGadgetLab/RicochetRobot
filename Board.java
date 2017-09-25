package robot;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Ricochet robot Java version.
 * @author Simon
 * @version 1.02
 */
public class Board extends JPanel{

    private final int WIDTH = 1800, HEIGHT = 800;
    private Board board;
    int rx, ry, yx, yy, bx, by, gx, gy, count, count2, genCount;
    private int currentRX, currentRY, currentYX, currentYY, currentBX, currentBY, currentGX, currentGY;
    private Timer timer, timer2, timer3, timer4, timer5, timer6, timer7, timer8;
    private Timer timer9, timer10, timer11, timer12, timer13, timer14, timer15, timer16;
    private JButton rbutton, ybutton, bbutton, gbutton, p1Increase, p1Decrease, p2Increase, p2Decrease, gen;
    private JButton rbuttonLeft, rbuttonRight, rbuttonUp, rbuttonDown;
    private JButton ybuttonLeft, ybuttonRight, ybuttonUp, ybuttonDown;
    private JButton bbuttonLeft, bbuttonRight, bbuttonUp, bbuttonDown;
    private JButton gbuttonLeft, gbuttonRight, gbuttonUp, gbuttonDown;
    private JPanel panel;
    private int arr[][] = { {0,0,1,100,0,0,0,0,1,100,0,0,0,0,0,0},//1
                            {0,0,0,0,1,110,0,0,0,0,1,1100,0,0,0,10},//2
                            {0,0,0,0,0,1000,0,11,100,0,0,0,0,0,10,1000},//3
                            {10,0,0,0,0,0,0,1000,0,0,0,0,0,0,1001,100},//4
                            {1000,0,0,11,100,0,10,0,0,11,100,0,0,0,0,0},//5
                            {0,10,0,1000,0,1,1100,0,0,1000,0,0,0,0,0,0},//6
                            {0,1001,100,0,0,0,0,10,10,0,0,1,110,0,0,0},//7
                            {0,0,0,0,0,0,1,1111,1111,100,0,0,1000,0,0,0},//8 center top
                            {0,10,0,0,0,10,1,1111,1111,100,0,0,0,0,0,0},//9 center bot
                            {0,1001,100,0,1,1100,0,1000,1000,0,0,0,0,0,0,10},//10
                            {10,0,0,0,0,0,0,0,11,100,0,0,0,10,0,1000},//11
                            {1000,0,0,0,0,0,0,0,1000,0,0,0,1,1100,0,0},//12
                            {0,0,0,0,0,0,11,100,0,0,0,0,0,0,0,0},//13
                            {0,0,0,0,0,0,1000,0,1,110,0,0,0,0,10,0},//14
                            {0,1,110,0,0,0,0,0,0,1000,0,0,0,0,1001,100},//15
                            {0,0,1000,0,0,1,100,0,0,0,0,1,100,0,0,0}//16
                           
                           };

    
    public Board(){
   
        rx = 5;
        ry = 5;
        yx = 5;
        yy = 55;
        bx = 5;
        by = 105;
        gx = 5;
        gy = 155;
        
        currentYY = 1;
        currentBY = 2;
        currentGY = 3;
        //red
        timer = new Timer(20, new rButtonRightListener());
        timer2 = new Timer(20, new rButtonLeftListener());
        timer3 = new Timer(20, new rButtonUpListener());
        timer4 = new Timer(20, new rButtonDownListener());
        //yellow
        timer5 = new Timer(20, new yButtonRightListener());
        timer6 = new Timer(20, new yButtonLeftListener());
        timer7 = new Timer(20, new yButtonUpListener());
        timer8 = new Timer(20, new yButtonDownListener());
        //blue
        timer9 = new Timer(20, new bButtonRightListener());
        timer10 = new Timer(20, new bButtonLeftListener());
        timer11 = new Timer(20, new bButtonUpListener());
        timer12 = new Timer(20, new bButtonDownListener());
        //green
        timer13 = new Timer(20, new gButtonRightListener());
        timer14 = new Timer(20, new gButtonLeftListener());
        timer15 = new Timer(20, new gButtonUpListener());
        timer16 = new Timer(20, new gButtonDownListener());
        
        //window size
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        //score tracker
        //p1
        p1Increase = new JButton("ADD");
        p1Increase.addActionListener(new p1IncreaseListener());
        super.add(p1Increase);
        p1Decrease = new JButton("MINUS");
        p1Decrease.addActionListener(new p1DecreaseListener());
        super.add(p1Decrease);
        
        p1Increase.setBounds(970, 620, 100, 40);
        p1Decrease.setBounds(970, 720, 100, 40);
        //p2
        p2Increase = new JButton("ADD");
        p2Increase.addActionListener(new p2IncreaseListener());
        super.add(p2Increase);
        p2Decrease = new JButton("MINUS");
        p2Decrease.addActionListener(new p2DecreaseListener());
        super.add(p2Decrease);
        
        p2Increase.setBounds(1070, 620, 100, 40);
        p2Decrease.setBounds(1070, 720, 100, 40);
        
        //label generator
        gen = new JButton("Next!");
        gen.addActionListener(new genListener());
        super.add(gen);
        gen.setBounds(1270, 720, 100, 40);
        
        // red control buttons
        rbutton = new JButton("Red");
        super.add(rbutton);
        rbuttonRight = new JButton("Right");
        rbuttonRight.addActionListener(new rButtonRightListener());
        super.add(rbuttonRight);
        rbuttonLeft = new JButton("Left");
        rbuttonLeft.addActionListener(new rButtonLeftListener());
        super.add(rbuttonLeft);
        rbuttonUp = new JButton("Up");
        rbuttonUp.addActionListener(new rButtonUpListener());
        super.add(rbuttonUp);
        rbuttonDown = new JButton("Down");
        rbuttonDown.addActionListener(new rButtonDownListener());
        super.add(rbuttonDown);
        
        
        //yellow control buttons
        ybutton = new JButton("Yellow");
        super.add(ybutton);
        ybuttonRight = new JButton("Right");
        ybuttonRight.addActionListener(new yButtonRightListener());
        super.add(ybuttonRight);
        ybuttonLeft = new JButton("Left");
        ybuttonLeft.addActionListener(new yButtonLeftListener());
        super.add(ybuttonLeft);
        ybuttonUp = new JButton("Up");
        ybuttonUp.addActionListener(new yButtonUpListener());
        super.add(ybuttonUp);
        ybuttonDown = new JButton("Down");
        ybuttonDown.addActionListener(new yButtonDownListener());
        super.add(ybuttonDown);
        
        //blue control buttons
        bbutton = new JButton("Blue");
        super.add(bbutton);
        bbuttonRight = new JButton("Right");
        bbuttonRight.addActionListener(new bButtonRightListener());
        super.add(bbuttonRight);
        bbuttonLeft = new JButton("Left");
        bbuttonLeft.addActionListener(new bButtonLeftListener());
        super.add(bbuttonLeft);
        bbuttonUp = new JButton("Up");
        bbuttonUp.addActionListener(new bButtonUpListener());
        super.add(bbuttonUp);
        bbuttonDown = new JButton("Down");
        bbuttonDown.addActionListener(new bButtonDownListener());
        super.add(bbuttonDown);
        
        //green control buttons
        gbutton = new JButton("green");
        super.add(gbutton);
        gbuttonRight = new JButton("Right");
        gbuttonRight.addActionListener(new gButtonRightListener());
        super.add(gbuttonRight);
        gbuttonLeft = new JButton("Left");
        gbuttonLeft.addActionListener(new gButtonLeftListener());
        super.add(gbuttonLeft);
        gbuttonUp = new JButton("Up");
        gbuttonUp.addActionListener(new gButtonUpListener());
        super.add(gbuttonUp);
        gbuttonDown = new JButton("Down");
        gbuttonDown.addActionListener(new gButtonDownListener());
        super.add(gbuttonDown);
        
        super.setLayout(null);
        //red buttons position
        rbuttonRight.setBounds(1110, 110, 100, 40);
        rbuttonLeft.setBounds(890, 110, 100, 40);
        rbuttonUp.setBounds(1000, 30, 100, 40);
        rbuttonDown.setBounds(1000, 190, 100, 40);
        rbutton.setBounds(1000, 80, 100, 100);
        // yellow buttons position
        ybutton.setBounds(1000, 350, 100, 100);
        ybuttonRight.setBounds(1110, 380, 100, 40);
        ybuttonLeft.setBounds(890, 380, 100, 40);
        ybuttonUp.setBounds(1000, 300, 100, 40);
        ybuttonDown.setBounds(1000, 460, 100, 40);
        //blue buttons position
        bbutton.setBounds(1400, 80, 100, 100);
        bbuttonRight.setBounds(1510, 110, 100, 40);
        bbuttonLeft.setBounds(1290, 110, 100, 40);
        bbuttonUp.setBounds(1400, 30, 100, 40);
        bbuttonDown.setBounds(1400, 190, 100, 40);
        //green buttons position
        gbutton.setBounds(1400, 350, 100, 100);
        gbuttonRight.setBounds(1510, 380, 100, 40);
        gbuttonLeft.setBounds(1290, 380, 100, 40);
        gbuttonUp.setBounds(1400, 300, 100, 40);
        gbuttonDown.setBounds(1400, 460, 100, 40);
    }
    // -------------------------------------------------------------------
    // Draws the game board
    // -------------------------------------------------------------------
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
       
        //Grid
        for(int i = 1; i < 16; i++){
            page.drawLine(i*50,0,i*50,800);
            page.drawLine(0,i*50,800,i*50);
        }
        //middle block
        page.fillRect(350,350,100,100);
        //4 sides
        page.fillRect(0, 0, 3, 800);
        page.fillRect(797, 0, 3, 800);
        page.fillRect(0, 797, 800, 3);
        page.fillRect(0, 0, 800, 3);
        
        //Obstacle
        //row1
        page.fillRect(150,0,6,50);
        page.fillRect(450,0,6,50);
        //row2
        page.fillRect(250,50,6,50);
        page.fillRect(250,94,50,6);
        page.fillRect(550,50,6,50);
        page.fillRect(550,50,50,6);
        page.fillRect(750,94,50,6);
        //row3
        page.fillRect(350,144,50,6);
        page.fillRect(394,100,6,50);
        //row4
        page.fillRect(0,194,50,6);
        page.fillRect(700,150,50,6);
        page.fillRect(744,150,6,50);
        //row5
        page.fillRect(150,244,50,6);
        page.fillRect(194,200,6,50);
        page.fillRect(450,244,50,6);
        page.fillRect(494,200,6,50);
        //row6
        page.fillRect(300,250,6,50);
        page.fillRect(300,250,50,6);
        //row7
        page.fillRect(50,300,50,6);
        page.fillRect(94,300,6,50);
        page.fillRect(600,300,6,50);
        page.fillRect(600,344,50,6);
        //row10
        page.fillRect(50,450,50,6);
        page.fillRect(94,450,6,50);
        page.fillRect(250,450,6,50);
        page.fillRect(250,450,50,6);
        page.fillRect(750,494,50,6);
        //row11
        page.fillRect(0,544,50,6);
        page.fillRect(400,544,50,6);
        page.fillRect(444,500,6,50);
        //row12
        page.fillRect(650,550,6,50);
        page.fillRect(650,550,50,6);
        //row13
        page.fillRect(300,644,50,6);
        page.fillRect(344,600,6,50);
        //row14
        page.fillRect(450,650,6,50);
        page.fillRect(450,694,50,6);
        //row15
        page.fillRect(100,700,6,50);
        page.fillRect(100,744,50,6);
        page.fillRect(700,700,50,6);
        page.fillRect(744,700,6,50);
        //row16
        page.fillRect(294,750,6,50);
        page.fillRect(600,750,6,50);
        
        
        //letter labels
        String str = "B1B2B3B4R1R2R3R4G1G2G3G4Y1Y2Y3Y4W";
        char[] charArray = str.toCharArray();
        page.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        page.drawChars(charArray, 0, 2, 260, 85);//B1
        page.drawChars(charArray, 8, 2, 560, 85);//R1
        page.drawChars(charArray, 32, 1, 360, 135);//W
        page.drawChars(charArray, 18, 2, 705, 185);
        page.drawChars(charArray, 10, 2, 155, 235);
        page.drawChars(charArray, 2, 2, 455, 235);
        page.drawChars(charArray, 16, 2, 310, 285);
        page.drawChars(charArray, 24, 2, 55, 335);
        page.drawChars(charArray, 26, 2, 610, 335);     
        page.drawChars(charArray, 28, 2, 55, 485);
        page.drawChars(charArray, 4, 2, 260, 485);
        page.drawChars(charArray, 30, 2, 405, 535);
        page.drawChars(charArray, 6, 2, 660, 585);
        page.drawChars(charArray, 12, 2, 305, 635);
        page.drawChars(charArray, 20, 2, 460, 685);
        page.drawChars(charArray, 22, 2, 110, 735);
        page.drawChars(charArray, 14, 2, 705, 735);
        
        //label generator
        String labelGen = "R4Y1B2G2B3Y3R1G1B1Y4G3R2B4G4Y2R3W ";
        char[] charArray2 = labelGen.toCharArray();
        page.drawChars(charArray2, genCount, 2, 1305, 700);//
        page.drawString("Generator", 1265, 650);//
        
        //SCORE TRAKCER
        String strPlayer = "P1P2P3P4";
        char[] scorePlayer = strPlayer.toCharArray();
        
        String strNum = Integer.toString(count);
        String strNum2 = Integer.toString(count2);
        
        page.drawChars(scorePlayer, 0, 2, 1005, 600);//
        page.drawString(strNum, 1005, 700);//
        page.drawString("Score", 850, 700);//
        page.drawChars(scorePlayer, 2, 2, 1105, 600);//
        page.drawString(strNum2, 1105, 700);//
        
        //COLOR TOKENS
        page.setColor(Color.RED);
        page.fillOval(rx,ry,40,40);
        page.setColor(Color.YELLOW);
        page.fillOval(yx,yy,40,40);
        page.setColor(Color.BLUE);
        page.fillOval(bx,by,40,40);
        page.setColor(Color.GREEN);
        page.fillOval(gx,gy,40,40);
        
    }

//    private class BoardListener implements ActionListener {
//
//        /**
//         * Updates the position of the image and possibly the direction
//         * of movement whenever the timer fires an action event.
//         */
//        public void actionPerformed(ActionEvent event) {
//
////            if(x1<650)
////                x1+=20;
////            repaint();
//        }
//    }
//    
    private class rButtonRightListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer.start();
            timer2.stop();
            timer3.stop();
            timer4.stop();
            
                     
          if((arr[currentRY][currentRX] % 10 < 1) && rx < 750){
              if(rightCollision(currentRX, currentRY)){
                
                      rx+=50;
                      currentRX += 1;
                      repaint();
                  
              } else {
                  timer.stop();
              }
          }
          
        }
        
    }
    
    private class rButtonLeftListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer.stop();
          timer3.stop();
          timer4.stop();
          timer2.start();
            
              if((arr[currentRY][currentRX] % 1000 ) < 100 && rx > 5){
                  if(leftCollision(currentRX, currentRY)){
                  rx-=50;
                  currentRX -= 1;
                  repaint();
                  } else {
                      timer2.stop();
                  }
              }
    
        }
    }
    
    private class rButtonUpListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer.stop();
          timer2.stop();
          timer4.stop();
          timer3.start();
                     
          if((arr[currentRY][currentRX] % 10000 < 1000) && ry > 5){
              if(upCollision(currentRX, currentRY)){
              ry-=50;
              currentRY -= 1;
              repaint();
              } else {
                  timer3.stop();
              }
          }
          
        }
        
    }
    
    private class rButtonDownListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer.stop();
          timer2.stop();
          timer3.stop();
          timer4.start();
                     
          if((arr[currentRY][currentRX] % 100 < 10) && ry < 750){
              if(downCollision(currentRX, currentRY)){
              ry+=50;
              currentRY += 1;
              repaint();
              } else {
                  timer4.stop();
              }
          }
          
        }
        
    }
   
    
    

    
//----------------------Yellow------------------------
    
    private class yButtonRightListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer5.start();
            timer6.stop();
            timer7.stop();
            timer8.stop();
            
                     
          if((arr[currentYY][currentYX] % 10 < 1) && yx < 750){
              if(rightCollision(currentYX, currentYY)){
                  yx+=50;
                  currentYX += 1;
                  repaint();
              } else {
                  timer5.stop();
              }
          }
          
        }
        
    }
    
    private class yButtonLeftListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer5.stop();
          timer7.stop();
          timer8.stop();
          timer6.start();
            
              if((arr[currentYY][currentYX] % 1000 ) < 100 && yx > 5){
                  if(leftCollision(currentYX, currentYY)){
                      yx-=50;
                      currentYX -= 1;
                      repaint();
                  } else {
                      timer6.stop();
                  }
              }
    
        }
    }
    
    private class yButtonUpListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer6.stop();
          timer5.stop();
          timer8.stop();
          timer7.start();
                     
          if((arr[currentYY][currentYX] % 10000 < 1000) && yy > 5){
              if(upCollision(currentYX, currentYY)){
                  yy-=50;
                  currentYY -= 1;
                  repaint();
              } else {
                  timer7.stop();
              }
          }
          
        }
        
    }
    
    private class yButtonDownListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer5.stop();
          timer6.stop();
          timer7.stop();
          timer8.start();
                     
          if((arr[currentYY][currentYX] % 100 < 10) && yy < 750){
              if(downCollision(currentYX, currentYY)){
                  yy+=50;
                  currentYY += 1;
                  repaint();
              } else {
                  timer8.stop();
              }
          }
          
        }
        
    }

    
//----------------------blue------------------------
    
    private class bButtonRightListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer9.start();
            timer10.stop();
            timer11.stop();
            timer12.stop();
            
                     
          if((arr[currentBY][currentBX] % 10 < 1) && bx < 750){
              if(rightCollision(currentBX, currentBY)){
                  bx+=50;
                  currentBX += 1;
                  repaint();
              } else {
                  timer9.stop();
              }
          }
          
        }
        
    }
    
    private class bButtonLeftListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer9.stop();
          timer11.stop();
          timer12.stop();
          timer10.start();
            
              if((arr[currentBY][currentBX] % 1000 ) < 100 && bx > 5){
                  if(leftCollision(currentBX, currentBY)){
                      bx-=50;
                      currentBX -= 1;
                      repaint();
                  } else {
                      timer10.stop();
                  }
              }
    
        }
    }
    
    private class bButtonUpListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer9.stop();
          timer10.stop();
          timer12.stop();
          timer11.start();
                     
          if((arr[currentBY][currentBX] % 10000 < 1000) && by > 5){
              if(upCollision(currentBX, currentBY)){
                  by-=50;
                  currentBY -= 1;
                  repaint();
              } else {
                  timer11.stop();
              }
          }
          
        }
        
    }
    
    private class bButtonDownListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer10.stop();
          timer11.stop();
          timer9.stop();
          timer12.start();
                     
          if((arr[currentBY][currentBX] % 100 < 10) && by < 750){
              if(downCollision(currentBX, currentBY)){
                  by+=50;
                  currentBY += 1;
                  repaint();
              } else {
                  timer12.stop();
              }
          }
          
        }
        
    }
    
    
//----------------------green------------------------
    
    private class gButtonRightListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            timer13.start();
            timer14.stop();
            timer15.stop();
            timer16.stop();
            
                     
          if((arr[currentGY][currentGX] % 10 < 1) && gx < 750){
              if(rightCollision(currentGX, currentGY)){
                  gx+=50;
                  currentGX += 1;
                  repaint();
              } else {
                  timer13.stop();
              }
          }
          
        }
        
    }
    
    private class gButtonLeftListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer13.stop();
          timer15.stop();
          timer16.stop();
          timer14.start();
            
              if((arr[currentGY][currentGX] % 1000 ) < 100 && gx > 5){
                  if(leftCollision(currentGX, currentGY)){
                      gx-=50;
                      currentGX -= 1;
                      repaint();
                  } else {
                      timer14.stop();
                  }
              }
    
        }
    }
    
    private class gButtonUpListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer13.stop();
          timer14.stop();
          timer16.stop();
          timer15.start();
                     
          if((arr[currentGY][currentGX] % 10000 < 1000) && gy > 5){
              if(upCollision(currentGX, currentGY)){
                  gy-=50;
                  currentGY -= 1;
                  repaint();
              } else {
                  timer15.stop();
              }
          }
          
        }
        
    }
    
    private class gButtonDownListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
          timer13.stop();
          timer14.stop();
          timer15.stop();
          timer16.start();
                     
          if((arr[currentGY][currentGX] % 100 < 10) && gy < 750){
              if(downCollision(currentGX, currentGY)){
                  gy+=50;
                  currentGY += 1;
                  repaint();
              } else {
                  timer16.stop();
              }
          }
          
        }
        
    }
    
//----------------------------Score trakcer-----------------------
    private class p1IncreaseListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
               count++;
               repaint();
        }
        
    }
    
    private class p1DecreaseListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
               count--;
               repaint();
        }
        
    }
    //p2
    private class p2IncreaseListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
               count2++;
               repaint();
        }
        
    }
    
    private class p2DecreaseListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
               count2--;
               repaint();
        }
        
    }
    
//----------------------------- generator --------------------------------------
    
    private class genListener implements ActionListener {
        
        public void actionPerformed(ActionEvent event) {
            genCount += 2;
            if(genCount > 32){
                genCount = 0;
            }
               repaint();
        }
        
    }
//--------------------------collision functions-------------------------------
    
    
    /**
     *  checks for right(east) collision of specific robot.
     * @param x1 current x position
     * @param y1 current y position
     * @return true if no right collision occurred
     */
    private boolean rightCollision(int x1, int y1){//check red
        if(x1 == currentRX && y1 == currentRY){
            if( ((x1 + 1 != currentYX && currentYY == y1) || (y1 != currentYY)) 
                    && ((x1 + 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 + 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentYX && y1 == currentYY){// check yellow
            if( ((x1 + 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 + 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 + 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentBX && y1 == currentBY){// check blue
            if( ((x1 + 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 + 1 != currentYX && currentYY == y1) || (y1 != currentYY)) 
                    && ((x1 + 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentGX && y1 == currentGY){// check green
            if( ((x1 + 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 + 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 + 1 != currentYX && currentYY == y1) || (y1 != currentYY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else {
            return false;
        }       
    }
    
    
    /**
     *  checks for left(west) collision of specific robot.
     * @param x1 current x position
     * @param y1 current y position
     * @return true if no left collision occurred
     */
    private boolean leftCollision(int x1, int y1){
        if(x1 == currentRX && y1 == currentRY){// check red
            if( ((x1 - 1 != currentYX && currentYY == y1) || (y1 != currentYY)) 
                    && ((x1 - 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 - 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentYX && y1 == currentYY){// check yellow
            if( ((x1 - 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 - 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 - 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentBX && y1 == currentBY){// check blue
            if( ((x1 - 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 - 1 != currentYX && currentYY == y1) || (y1 != currentYY)) 
                    && ((x1 - 1 != currentGX && currentGY == y1) || (y1 != currentGY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentGX && y1 == currentGY){// check green
            if( ((x1 - 1 != currentRX && currentRY == y1) || (y1 != currentRY)) 
                    && ((x1 - 1 != currentBX && currentBY == y1) || (y1 != currentBY)) 
                    && ((x1 - 1 != currentYX && currentYY == y1) || (y1 != currentYY)) ){ 

                return true;
           
            } else {
                return false;
            }
        } else {
            return false;
        }       
    }
    
    /**
     *  checks for up(north) collision of specific robot.
     * @param x1 current x position
     * @param y1 current y position
     * @return true if no up collision occurred
     */
    private boolean upCollision(int x1, int y1){
        if(x1 == currentRX && y1 == currentRY){//check red
            if( ((x1 != currentYX) || (x1 == currentYX && currentYY != y1 - 1)) 
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 -1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 -1))){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentYX && y1 == currentYY){// check yellow
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 -1)) 
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 -1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 -1))){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentBX && y1 == currentBY){//check blue
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 -1)) 
                    && ((x1 != currentYX ) || (x1 == currentYX && currentYY != y1 -1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 -1))){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentGX && y1 == currentGY){// check green
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 -1)) 
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 -1))
                    && ((x1 != currentYX ) || (x1 == currentYX && currentYY != y1 -1))){ 

                return true;
           
            } else {
                return false;
            }
        } else {
            return false;
        }       
    }//up collision ends
    
    
    /**
     *  checks for down(south) collision of specific robot.
     * @param x1 current x position
     * @param y1 current y position
     * @return true if no down collision occurred
     */
    private boolean downCollision(int x1, int y1){// check red
        if(x1 == currentRX && y1 == currentRY){
            if( ((x1 != currentYX) || (x1 == currentYX && currentYY != y1 +1)) 
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 +1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 +1))){ 

                return true;
           
            } else {
                return false;
            }
         
        } else if (x1 == currentYX && y1 == currentYY){//check yellow
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 +1)) 
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 +1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 +1))){ 

                return true;
           
            } else {
                return false;
            }
        } else if (x1 == currentBX && y1 == currentBY){// check for blue
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 +1)) 
                    && ((x1 != currentYX ) || (x1 == currentYX && currentYY != y1 + 1))
                    && ((x1 != currentGX ) || (x1 == currentGX && currentGY != y1 + 1))){ 

                return true;
           
            } else {
                return false;
            }  
        } else if (x1 == currentGX && y1 == currentGY){// check green
            if( ((x1 != currentRX) || (x1 == currentRX && currentRY != y1 +1)) 
                    && ((x1 != currentYX ) || (x1 == currentYX && currentYY != y1 + 1))
                    && ((x1 != currentBX ) || (x1 == currentBX && currentBY != y1 + 1))){ 

                return true;
           
            } else {
                return false;
            }  
        
        } else {
            return false;
        }       
    }//down collision ends
    
    
    
}//board ends

