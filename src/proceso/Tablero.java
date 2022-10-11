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
import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable{
    private final Image background;
    public  ArrayList<Folk> folks;
    public Folk[] folksIn;
    private Thread hilo;
    public Rectangle stage;
    public Door doors[];
    public boolean full=false;
    
    
    private  int x,y;
    
    public Tablero(){
        
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        background = new ImageIcon(this.getClass().getResource("/img/madonaStage.png")).getImage();
        stage=new Rectangle(75,33,316,200);
        doors=new Door[2];
        doors[0]=new Door(384,33,10,50,true,this);
        doors[1]=new Door(70,33,10,50,false,this);
        folksInit();
        hilo = new Thread(this);
        hilo.start();
   }
    
    void folksInit(){
        folks=new ArrayList<Folk>();
        folksIn=new Folk[10];
        for(int i=0;i<20;i++){
            folks.add(new Folk(0,0,this));
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(background, 0,0,getWidth(),getHeight(), null);
        g2.setColor(Color.red);
        g2.fill(stage);
        g2.setColor(Color.PINK);
        g2.fill(doors[0].hitbox);
        g2.fill(doors[1].hitbox);
        for(Folk f : folks){
            f.draw(g2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void ciclo(){
        for(Folk f : folks){
            f.tick();
        }
        full=lastFolk()==folksIn.length;
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
    public boolean full(){
        return false;
    }
    public void addFolk(Folk newFolk){
        if(!full){
            if(lastFolk()!=folksIn.length){
                folksIn[lastFolk()]=newFolk;
                System.out.println("table "+lastFolk()+" "+folksIn.length);
            }
        }
    }
    public void removeFolk(Folk folk){
        for(int i=0;i<folksIn.length;i++){
            if(folksIn[i]==folk){
                folksIn[i]=null;
            }
        }
    }
    public int lastFolk(){
        int i=0;
        for(i=0;i<folksIn.length;i++){
            if(folksIn[i]==null){
                break;
            }
        }
        return i;
    }
}
