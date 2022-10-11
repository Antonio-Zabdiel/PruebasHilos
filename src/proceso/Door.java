
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
    public void addFolk(Folk newFolk){
        if(lastFolk()==folks.length){
            release();
        }
        folks[lastFolk()]=newFolk;
        System.out.println("door "+lastFolk()+" "+folks.length);
    }
    public int lastFolk(){
        int i=0;
        for(i=0;i<folks.length;i++){
            if(folks[i]==null){
                break;
            }
        }
        return i;
    }
    public void release(){
        for(int i=0;i<folks.length;i++){
            folks[i].inDoor=false;
            folks[i].inStage=!folks[i].inStage;
            int shoot=Math.abs(folks[i].xDesp);
            if(left){
                shoot=shoot*(-1);
            }
            if(!folks[i].inStage){
                shoot=shoot*(-1);
            }
            if(folks[i].inStage){
                tablero.addFolk(folks[i]);
            }else{
                tablero.removeFolk(folks[i]);
            }
            folks[i].xDesp=shoot;
            folks[i].moveY(hitbox.height+folks[i].sprite.getHeight(tablero));
            folks[i].moveY(hitbox.height+folks[i].sprite.getHeight(tablero));
            folks[i]=null;
        }
    }
}