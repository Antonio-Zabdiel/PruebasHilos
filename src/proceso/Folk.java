/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proceso;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class Folk {
    private Image sprite;
    private int x,y,xDesp,yDesp;
    private Tablero tablero;
    public boolean die=false;
    
    public Folk(int x, int y, Tablero tablero){
        this.x=x;
        this.y=y;
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
        xDesp=(int)(Math.random()*10)-1;
        moveX(xDesp);
        yDesp+=(int)(Math.random()*10)-1;
        moveY(yDesp);
        if(x<0||x+sprite.getWidth(null)>tablero.getWidth()){
            die=true;
        }
        if(y<0||y+sprite.getHeight(null)>tablero.getHeight()){
            die=true;
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
