/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author pragu
 */
public class GameWorking extends JPanel implements KeyListener,ActionListener{
    private ImageIcon title;
    private int[] lengthx=new int[750];
    private int[] lengthy=new int[750];
    private boolean left=false;
    private boolean right=false;
    private boolean top=false;
    private boolean down=false;
    private ImageIcon rightm;
    private ImageIcon leftm;
    private ImageIcon downm;
    private ImageIcon upm;
    private Timer time;
    private int delay=120;
    private ImageIcon snakeimage;
    private int length=3;
    private int moves=0;
    private boolean play=false,t=false;
    private ImageIcon food;
    private Random random= new Random();
    private int foodx= 25+(random.nextInt(34)*25) ;
    private int foody=75+(random.nextInt(23)*25);
    private int score;
    //private int 
    public GameWorking(){
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        //this.requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
        
        time=new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g){
        //start
        if(moves==0){
            restart();
        }
        //border
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);
        
        //title
        title=new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/snaketitle.jpg")));
        title.paintIcon(this, g, 25, 11);
        
        //border
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);
        
        //background
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);
        
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif: ", Font.PLAIN, 15));
        g.drawString("Score: "+score,780,30);
        
        //length of snake
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.PLAIN, 15));
        g.drawString("Length: "+length,780,50);
        
        //exit
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.PLAIN, 15));
        g.drawString("Exit: Esc ",30,30);
        
        //restart
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.PLAIN, 15));
        g.drawString("Restart: Enter ",30,50);
        
        //snake
        rightm = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rightmouth.png")));
        rightm.paintIcon(this, g, lengthx[0], lengthy[0]);
        for (int i = 0; i < length; i++) {
            if (i == 0 && right) {
                rightm = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/rightmouth.png")));
                rightm.paintIcon(this, g, lengthx[i], lengthy[i]);
            }
            if (i == 0 && left) {
                leftm = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/leftmouth.png")));
                leftm.paintIcon(this, g, lengthx[i], lengthy[i]);
            }
            if (i == 0 && down) {
                downm = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/downmouth.png")));
                downm.paintIcon(this, g, lengthx[i], lengthy[i]);
            }
            if (i == 0 && top) {
                upm = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/upmouth.png")));
                upm.paintIcon(this, g, lengthx[i], lengthy[i]);
            }
            if(i!=0){
                snakeimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/snakeimage.png")));
                snakeimage.paintIcon(this, g, lengthx[i], lengthy[i]);
            }
        }
        food = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/enemy.png")));
        if(foodx==lengthx[0] && foody==lengthy[0]){
            score+=5;
            length++;
            foodx= 25+(random.nextInt(34)*25) ;
            foody=75+(random.nextInt(23)*25);
        }
        food.paintIcon(this, g, foodx, foody);
        if(!play && t){
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("Game Paused", 300, 300);
            
        }
        for(int i=1;i<length;i++){
            if(lengthx[i]==lengthx[0] && lengthy[i]==lengthy[0]){
                //restart();
                g.setColor(Color.white);
                g.setFont(new Font("serif", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Score: " + score, 350, 350);
                restart();
            }
        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        play=true;
        t=false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if(!left){
                right=true;
            }
            else{
                right=false;
                left=true;
            }
            //left=false;
            top=false;
            down=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if(!right){
                left=true;
            }
            else{
                left=false;
                right=true;
            }
            //left=false;
            top=false;
            down=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_UP){
            moves++;
            top=true;
            if(!down){
                top=true;
            }
            else{
                top=false;
                down=true;
            }
            //left=false;
            right=false;
            left=false;
        }
        if(e.getKeyCode()== KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if(!top){
                down=true;
            }
            else{
                down=false;
                top=true;
            }
            //left=false;
            right=false;
            left=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                repaint();
            }
            if(play){
                //play=false;
                restart();
                repaint();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            play=false;
            t=true;
            if(JOptionPane.showConfirmDialog(this, "EXIT?", "Game", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                System.exit(0);
            }
            repaint();
        }
    }
    public void restart(){
        score=0;
        length=3;
        play=false;
        lengthx[2]=50;
        lengthx[1]=75;
        lengthx[0]=100;
        lengthy[2]=100;
        lengthy[1]=100;
        lengthy[0]=100;
        right=true;
        left=false;
        top=false;
        down=false;
        foodx= 25+(random.nextInt(34)*25) ;
        foody=75+(random.nextInt(23)*25);
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        time.start();
        if(play){
            if(length==20){
                delay-=10;
            }
            if(length==40){
                delay-=10;
            }
            if(length==50){
                delay-=10;
            }
        if(right){
            for(int i=length-1;i>=0;i--){
                lengthy[i+1]=lengthy[i];
            }
            for(int i=length;i>=0;i--){
                if(i==0){
                    lengthx[i]=lengthx[i]+25;
                }
                if(i!=0){
                   lengthx[i]=lengthx[i-1];
                }
                if(lengthx[i]>850){
                    lengthx[i]=25;
                }
                //lengthy[i+1]=lengthy[i];
            }
            repaint();
        }
        if(left){
            for(int i=length-1;i>=0;i--){
                lengthy[i+1]=lengthy[i];
            }
            for(int i=length;i>=0;i--){
                if(i==0){
                    lengthx[i]=lengthx[i]-25;
                }
                if(i!=0){
                   lengthx[i]=lengthx[i-1];
                }
                if(lengthx[i]<25){
                    lengthx[i]=850;
                }
                //lengthy[i+1]=lengthy[i];
            }
            repaint();
        }
        if(top){
            for(int i=length-1;i>=0;i--){
                lengthx[i+1]=lengthx[i];
            }
            for(int i=length;i>=0;i--){
                if(i==0){
                    lengthy[i]=lengthy[i]-25;
                }
                if(i!=0){
                   lengthy[i]=lengthy[i-1];
                }
                if(lengthy[i]<75){
                    lengthy[i]=625;
                }
                //lengthy[i+1]=lengthy[i];
            }
            repaint();
        }
        if(down){
            for(int i=length-1;i>=0;i--){
                lengthx[i+1]=lengthx[i];
            }
            for(int i=length;i>=0;i--){
                if(i==0){
                    lengthy[i]=lengthy[i]+25;
                }
                if(i!=0){
                   lengthy[i]=lengthy[i-1];
                }
                if(lengthy[i]>625){
                    lengthy[i]=75;
                }
                //lengthy[i+1]=lengthy[i];
            }
            repaint();
        }
        }
       // repaint();
    }
}
