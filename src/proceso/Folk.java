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
    private Image sprite;
    public int x,y,xDesp,yDesp;
    private Tablero tablero;
    public boolean inDoor=false,inStage=false;
    public Rectangle box;
    
    public Folk(int x, int y, Tablero tablero){
        this.x=x;
        this.y=y;
        xDesp=(int)(Math.random()*10)-5;
        yDesp+=(int)(Math.random()*10)-5;
        this.tablero=tablero;
        sprite = new ImageIcon(this.getClass().getResource("/img/stickPixel.png")).getImage();
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
        for(Door d: tablero.doors){
            if(d.hitbox.contains(x,y)){
                if(!inDoor){
                    d.addFolk(this);
                    inDoor=true;
                }
            }
        }
        if(!inDoor){
            moveX(xDesp);
            moveY(yDesp);
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
