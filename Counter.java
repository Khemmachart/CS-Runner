import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Counter that displays some taxt and a number.
 * 
 * @author Michael Kolling
 * @version 1.1
 */
public class Counter extends Actor
{
    private String text;

    public Counter () {
        this("");
    }

    public Counter (String prefix) {
        text = prefix;
        int imageWidth= (text.length() + 2) * 10;
        setImage(new GreenfootImage(imageWidth, 16));
    }

    public void update (int num) {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(text + num, 1, 12);
    }
}