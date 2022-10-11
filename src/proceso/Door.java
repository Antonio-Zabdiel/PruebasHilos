
package proceso;

import java.awt.Rectangle;

public class Door {
    public Rectangle hitbox;
    public Folk folks[];
    
    public Door(int x, int y, int width, int height){
        hitbox=new Rectangle(x,y,width,height);
        folks=new Folk[4];
    }
    public void addFolk(Folk newFolk){
        System.out.println(lastFolk()+" "+folks.length);
        folks[lastFolk()]=newFolk;
        if(lastFolk()==folks.length){
            release();
        }
    }
    public int lastFolk(){
        int i=0;
        boolean search=true;
        while(search){
            System.out.println(i+" "+folks.length);
            if(folks[i]==null||i==folks.length-1){
                search=false;
            }
            i++;
        }
        return i;
    }
    public void release(){
        for(Folk f : folks){
            f.inDoor=false;
            f=null;
        }
    }
}