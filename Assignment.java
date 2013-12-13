import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignment extends Actor
{
    private int speed;
    
    public Assignment (int speed) {
        this.speed = speed;
        setImage("bullet.png");
    }
    
    public void act() 
    {
        move(speed);
        checkObject();
    }
    
    public void checkObject () {
        Object object = (Object)getOneIntersectingObject(Object.class);
        if( object != null ) {
            if ( object instanceof CharPlayer ) {
                CharBot.setSlowSpeed(4);
                destroy();
            }
            else if ( object instanceof CharBot ) {
                //CharBot.setSlowSpeed(-4);
                //destroy();
            }
            else if ( object instanceof Ground ) {
                destroy();
            }
        }
    }
    
    public void checkWorldEdge () {
        if (getX() == 0 || getY() == 0 || getX() == getWorld().getWidth() || getY() == getWorld().getHeight()) {
            destroy();
        }
    }
    
    public void destroy () {
        getWorld().removeObject(this);
        Greenfoot.playSound("hw.mp3");
    }
}
