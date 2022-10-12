
package proceso;

import java.awt.Rectangle;

public class Door {
    public Rectangle hitbox;
    public Folk folks[];
    public boolean left;
    private Tablero tablero;
    
    public Door(int x, int y, int width, int height, boolean left,Tablero tablero){
        hitbox=new Rectangle(x,y,width,height);
        folks=new Folk[4];
        this.left=left;
        this.tablero=tablero;
    }
    public void release(){
        for(int i=0;i<folks.length;i++){
            
        }
    }
    public void tick(){
        if(!tablero.full(tablero.delta)){
            int desp=hitbox.width*3;
            if(!left)
                desp=desp*(-1);
            tablero.add(hitbox.x+desp, hitbox.y+(hitbox.height/2));
            tablero.folks[tablero.lastFolk()-1].xDesp=desp;
        }
    }
}