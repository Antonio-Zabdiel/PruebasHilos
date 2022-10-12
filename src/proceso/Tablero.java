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
    public int delta=200;
    public int max=1000;
    JLabel label1;
    Graphics2D g2;
    
    private  int x,y;
    
    public Tablero(int width, int height){
        max++;
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
        doors[1]=new Door(width-15,33,10,200,false,this);
        folksInit();
        hilo = new Thread(this);
        hilo.start();
   }
    
    void folksInit(){
        folks=new Folk[max];
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g2 = (Graphics2D)g;
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
        
        label1.setText("num: "+Integer.toString(lastFolkLabel()));
        System.out.println("num: "+Integer.toString(lastFolkLabel()));
        if(g2!=null)
            label1.paint(g2);
        for(int f=0;f<folks.length;f++){
            if(folks[f]!=null){
                folks[f].id=f;
                folks[f].tick();
            }
        }
        full=(lastFolk()==folks.length);
        for(Door d:doors){
            if(d!=null)
                d.tick();
        }
        clean();
        
            System.out.println(lastFolk()+" "+lastFolk2());
    }
    
    @Override
    public void run() {
        while(true){
            ciclo();
            repaint();
            try{
                Thread.sleep(20);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public int lastFolk(){
        int i=0;
        for(i=1;i<folks.length-1;i++){
            if(folks[i]==null){
                break;
            }
        }
        return i;
    }
    public int lastFolkLabel(){
        int i=0;
        for(i=1;i<folks.length-1;i++){
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
            if(folks[i]==null
                    //&&lastFolk()!=lastFolk2()
                    ){
                folks[i]=new Folk(0,100,this,0);
                folks[i].set(folks[lastFolk()-1]);
                folks[lastFolk()-1].killNoWitness();
                folks[i].id=i;
            }
        }
    }
}
