import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Ground extends Actor
{
    public static final int SPEED = 4;
    
    public Ground () {
        this("/bg/2_0.png");
    } 
    
    public Ground (String img) {
        setImage(img);
    } 
    
    public void act() 
    {
        moveBackward();
    }
    
    public void moveBackward () {
        if (getX() == -50) {
            getWorld().removeObject(this);
        }
        else {
            setLocation (getX()-SPEED, getY());
        }
    }
}
