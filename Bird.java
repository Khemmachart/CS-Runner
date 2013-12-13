import java.util.ArrayList;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Character
{
    private ArrayList<GreenfootImage> sprite = new ArrayList<GreenfootImage>();
    private int imgCount;
    private int timeCount;
    
    public Bird () {
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/01.png"));
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/02.png"));
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/03.png"));
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/04.png"));
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/05.png"));
        sprite.add(new GreenfootImage("images/char_sheet/char_bird/06.png"));
        setImage(sprite.get(imgCount));
    }

    public void act() 
    {
        changeImages();
    }    
    
    public void changeImages () {
        if (timeCount++ % 9 == 0) {
            imgCount = (++imgCount)%4;
            setImage(sprite.get(imgCount));
        } 
    }
}
