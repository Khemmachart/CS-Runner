import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharBot extends Character
{
    private ArrayList<GreenfootImage> sprite = new ArrayList<GreenfootImage>();
    private Counter assignmentCounter;
    
    private int acceleration;
    private int jumpStrength;
    private int vSpeed;  
    private int imgCount;
    private int timeCount;
    private int score;
    private int fireCooldown;
    private boolean auto;
    private String keyFire;
    
    public static int assignment;
    public static int assignmentIncresement;
    public static int slowSpeed;
    public static int slowSpeedCooldown;
    
    public CharBot (boolean auto, String... img) {
        this.acceleration = 1;
        this.jumpStrength = 40;
        this.vSpeed       = 0;
        this.keyFire      = "l";
        this.auto         = auto;
        
        // Assign images to ArrayList call sprite and then, set the first image
        for (int i=0 ; i<img.length ; i++) {
            sprite.add(new GreenfootImage(img[i]));
        }
        setImage(sprite.get(0));
    }

    public void act() 
    {   
        // If mode game is PvP, another player can get key to fire
        if (auto) {
            canFire();
        }
        else {
            checkKeyFire();
        }
        
        // check jump and move
        if ( onGround() ) {
            changeImages();
            move(slowSpeed);
            timeKeeper();
        }
        else {
            checkFall();
        }
        
        updateCounter();
        checkWorldEdge();
    }    
    
    /**
     * This method will increase assignment of AI by game mode
     * if play with player its hard to win, so assignment will increase by 1
     * in the otherhand, if play with computer its mean easy to win, assignment will increase by 2
     */
    public void setAuto (boolean auto) {
        this.auto = auto;
        if (this.auto) {
            this.assignmentIncresement = 2;
        }
        else {
            this.assignmentIncresement = 1;
        }
    }
    
    /**
     * Return game mode of this game
     */
    public boolean getAuto () {
        return this.auto;
    }
    
    /**
     * If mode game is play with computer 
     */
    private void checkKeyFire () {
        if ( Greenfoot.isKeyDown(keyFire) ) {
            canFire();
        }
    }
    
    /** 
     * If not have cooldown and have at less one assignment, can fire
     */
    public void canFire () {
        if (fireCooldown < 0 && assignment > 0) {
           fire();
        }
    }
    
    public void fire () {
        ArrayList<Character> player = (ArrayList) getObjectsInRange(2000,Character.class);
        // Fistly, check about have any enemy in range or not
        if ( player.size() > 0 ) {
             // In play with computer mode, AI will have 3 percent for get fire,
             // but if game mode is play with another player, him/her can fire (send assignment back) by keyFire to first player
            if ( Greenfoot.getRandomNumber(100) > 97 || !auto) {
                // find the direction of enemy to fire to them, when you send assignmeent will decrease and fire cooldown will up to 10
                if ( player.get(0).getX() > getX() ) {
                    getWorld().addObject(new Assignment(5), getX(), getY());
                    assignment --;
                    fireCooldown = 10;
                }
                else if (  player.get(0).getX() < getX() ) {
                    getWorld().addObject(new Assignment(-5), getX(), getY());
                    assignment --;
                    fireCooldown = 10;
                }
            }
        }
    }
    
    public void changeImages () {
        if (timeCount++ % 9 == 0) {
            imgCount = (++imgCount)%4;
            setImage(sprite.get(imgCount));
        } 
    }
    
    public static void setSlowSpeed (int num) {
        slowSpeed = num;
        slowSpeedCooldown = 10;
        if (num < 0) {
            // In this case (num is negative) its mean AI was attacked, and must checked homework
            assignment += assignmentIncresement;
        }
    }
    
    public void timeKeeper () {
        slowSpeedCooldown --;
        fireCooldown --;
        if (slowSpeedCooldown < 0) {
            slowSpeed = 0;
        }
    }
    
    public int getAssignment () {
        return assignment;
    }

    private void jump() {
        Greenfoot.playSound("jump.mp3");
        setVSpeed(-jumpStrength);
        fall();
    }
    
    public void setVSpeed(int speed) {
        vSpeed = speed;
    }
    
    public void fall() {
        setLocation ( getX(), getY() + vSpeed/3);
        vSpeed = vSpeed + acceleration;
    }
    
    
    private void checkFall() {
        if (onGround()) {
            setVSpeed(0);
        }
        else {
            fall();
        }
    }
    
    public boolean onGround() {
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2+8 , Ground.class);
        Object underLast = getOneObjectAtOffset(0, getImage().getHeight()/2+8 , GroundLast.class);
        if (underLast != null) {
            jump();
            return true;
        }
        else if (under != null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void checkWorldEdge () {
        if ( getX() < 0 || getY() > getWorld().getHeight() ) {
            ((ScrollWorld)getWorld()).endGameWon();
        }
        else if ( getX() > getWorld().getWidth() ) {
            ((ScrollWorld)getWorld()).endGameLost();
        }
    }
    
    public void updateCounter () {
        if(assignmentCounter == null) {
             assignmentCounter = new Counter("Assignment: ");
             getWorld().addObject(assignmentCounter, getWorld().getWidth()-75, 35);
        }
        else {
            assignmentCounter.update(getAssignment());
        }
    }
}
