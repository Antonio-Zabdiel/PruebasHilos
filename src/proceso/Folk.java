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
    private int x,y;
    private Tablero tablero;
    
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
        
    }
    
    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }
}
