/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proceso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable{
    private final Image background;
    public Folk folks[];
    private Thread hilo;
    public Door doors[];
    public boolean full=false;
    public int delta=20;
    public int max=501;
    JLabel label1;
    
    private  int x,y;
    
    public Tablero(int width, int height){
        label1 = new JLabel();
        setLocation(100, 100);
        label1.setText("0");
        label1.setBounds(0, 0, 200, 50);
        add(label1);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        background = new ImageIcon(this.getClass().getResource("/img/cropedMadona.png")).getImage();
        doors=new Door[2];
        doors[0]=new Door(0,33,10,200,true,this);
        doors[1]=new Door(width-25,33,10,200,false,this);
        folksInit();
        hilo = new Thread(this);
        hilo.start();
   }
    
    void folksInit(){
        folks=new Folk[max];
        add(50,0);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(background, 0,0,getWidth(),getHeight(), null);
        g2.setColor(Color.PINK);
        g2.fill(doors[0].hitbox);
        g2.fill(doors[1].hitbox);
        for(Folk f : folks){
            if(f!=null)
            f.draw(g2);
        }
        label1.paint(g2);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void ciclo(){
        
        for(Folk f : folks){
            if(f!=null)
                f.tick();
        }
        full=(lastFolk()==folks.length);
        for(Door d:doors){
            if(d!=null)
                d.tick();
        }
        clean();
        label1.setText("num: "+Integer.toString(lastFolk2()));
    }
    
    @Override
    public void run() {
        while(true){
            ciclo();
            repaint();
            try{
                Thread.sleep(16);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public int lastFolk(){
        int i=0;
        for(i=0;i<folks.length-1;i++){
            if(folks[i]==null){
                break;
            }
        }
        return i;
    }
    public int lastFolk2(){
        int i=0;
        for(i=folks.length-1;i>0;i--){
            if(folks[i]!=null){
                break;
            }
        }
        return i;
    }
    public void add(int x, int y){
        int l=lastFolk();
        for(int i=0;i<delta;i++){
            folks[i+l]=new Folk(x,y,this,i+l);
        }
    }
    public boolean full(int add){
        return lastFolk()+add>folks.length;
    }
    public void clean(){
        for(int i=0;i<folks.length;i++){
            
            if(folks[i]==null&&lastFolk()!=lastFolk2()){
                folks[i]=new Folk(0,0,this,0);
                folks[i]=folks[lastFolk2()];
                folks[lastFolk2()].kill();
                System.out.println("i"+i);
                folks[i].id=i;
            }
        }
    }
}
