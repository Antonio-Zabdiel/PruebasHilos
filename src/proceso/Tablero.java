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
    private  ArrayList<Folk> folks;
    private Thread hilo;
    private Rectangle stage;
    
    private  int x,y;
    
    public Tablero(){
        
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        background = new ImageIcon(this.getClass().getResource("/img/madonaStage.png")).getImage();
        stage=new Rectangle(75,33,316,200);
        folksInit();
        hilo = new Thread(this);
        hilo.start();
   }
    
    void folksInit(){
        folks=new ArrayList<Folk>();
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
        for(Folk i : folks){
            i.draw(g2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void ciclo(){
        
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
}
