
package proceso;

import java.awt.Rectangle;

public class Door {
    public Rectangle hitbox;
    public Folk folks[];
    public boolean left;
    
    public Door(int x, int y, int width, int height, boolean left){
        hitbox=new Rectangle(x,y,width,height);
        folks=new Folk[4];
        this.left=left;
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
            int shoot=10;
            if(left){
                shoot=shoot*(-1);
            }
            if(folks[i].inStage){
                shoot=shoot*(-1);
            }
            folks[i].xDesp=shoot;
            folks[i].moveY(hitbox.height);
            folks[i]=null;
        }
    }
}