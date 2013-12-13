import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Coins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Items extends Actor
{
    private ArrayList<GreenfootImage> imgList = new ArrayList<GreenfootImage>();
    private int vSpeed;  
    private int acceleration = 2;
    private int time;
    private int currentImg = 0;
    private int delay = 2;
    
    public Items (int delay, String... img) {
        this.delay = delay;
        for (int i=0 ; i<img.length ; i++) {
            imgList.add(new GreenfootImage(img[i]));
        }
    }
    
    public void act() 
    {
        if (!checkWorldEdge()) {
            timeKeeper();
            changeImg();
            if( onGround() ) {
                move();
            }
            else {
                fall();
            }
        }    
        else {
            destroy();
        }
    }
    
    public void event () {
        // do nothing
    }
    
    // return number of object that was create
    public abstract int getAmount ();
    
    public void timeKeeper () {
        time = ( ++time ) % ( imgList.size() * delay );
        if(time % delay == 0) {
            currentImg = ++currentImg % imgList.size();
        }
    }
    
    public void changeImg () {
        setImage(imgList.get(currentImg));
    }
    
    public void setVSpeed(int speed) {
        vSpeed = speed;
    }
    
    public void fall() {
        setLocation ( getX(), getY() + vSpeed/3);
        vSpeed = vSpeed + acceleration;
    }
    
    public void move () {
        setLocation(getX()-Ground.SPEED, getY());
    }
    
    public boolean onGround() {
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2+8 , Ground.class);
        return under != null;
    }
    
    public void destroy () {
        getWorld().removeObject(this);
        event();
    }
    
    public boolean checkWorldEdge () {
        if ( getY()== 0 || getY()==getWorld().getHeight() || getX()==-20 ) {
            return true;
        }
        else {
            return false;
        }
    }
}
