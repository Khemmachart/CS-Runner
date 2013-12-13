import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public abstract class CharButton extends Button
{
    private ArrayList<GreenfootImage> sprite = new ArrayList<GreenfootImage>();
    private int imgCount;
    private int timeCount;
    
    /**
     * Subclass just sent every images that need to use in each char
     */
    public CharButton (String... img) {
        for (int i=0 ; i<img.length ; i++) {
            sprite.add(new GreenfootImage(img[i]));
        }
        setImage(sprite.get(0));
    }
    
    /**
     * THE METHOD THAT ANY SUBCLASS MUST IMPLEMENT
     */
    protected abstract void createChar();
    
    public void act() 
    {
        changeImages();
        doClick();
    }    
    
    /**
     * check the action of mouse and then create character
     */
    public void doClick(){
        if (Greenfoot.mouseClicked(this)) {
            createChar();
        }
    }
    
    public void changeImages () {
        if (timeCount++ % 9 == 0) {
            imgCount = (++imgCount)%4;
            setImage(sprite.get(imgCount));
        } 
    }
}
