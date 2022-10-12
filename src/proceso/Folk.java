/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proceso;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class Folk {
    public Image sprite;
    public int x,y,xDesp,yDesp,width,height;
    private Tablero tablero;
    public boolean inDoor=false;
    public int id=-1;
    public Rectangle hitbox;
    
    public Folk(int x, int y, Tablero tablero,int id){
        this.x=x;
        this.y=y;
        this.id=id;
        xDesp=(int)(Math.random()*10)-5;
        while(xDesp==0)
            xDesp=(int)(Math.random()*10)-5;
        yDesp+=(int)(Math.random()*10)-5;
        while(yDesp==0)
            yDesp=(int)(Math.random()*10)-5;
        this.tablero=tablero;
        sprite = new ImageIcon(this.getClass().getResource("/img/stickPixel.png")).getImage();
        width=sprite.getWidth(null);
        height=sprite.getHeight(null);
        hitbox=new Rectangle(x,y,width,height);
    }
    public void set(Folk f){
        this.x=f.x;
        this.y=f.y;
        this.hitbox=f.hitbox;
        this.id=f.id;
        this.inDoor=f.inDoor;
        this.tablero=f.tablero;
        this.xDesp=f.xDesp;
        this.yDesp=f.yDesp;
    }

    public int getX() {
        return x;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void moveY(int y) {
        this.y += y;
    }
    
    public void tick(){
        if(x<0||x+sprite.getWidth(null)>tablero.getWidth()){
            xDesp=xDesp*(-1);
        }
        if(y<0||y+sprite.getHeight(null)>tablero.getHeight()){
            yDesp=yDesp*(-1);
        }
        moveX(xDesp);
        moveY(yDesp);
        hitbox.x=x;
        hitbox.y=y;
        for(Door d:tablero.doors){
            if(d!=null){
                if(hitbox.intersects(d.hitbox)){
                    kill();
                }
            }
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
    public void kill(){
        System.out.println("im die "+id);
        tablero.folks[id]=null;
    }
}
