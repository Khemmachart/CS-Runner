import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CharPlayer extends Character
{
    private ArrayList<GreenfootImage> sprite = new ArrayList<GreenfootImage>();
    private Counter scoreCounter;
    private Counter assignmentCounter;
    private String keyJump;
    private String keyFire;
    
    private int acceleration;
    private int jumpStrength;
    private int vSpeed;  
    private int imgCount;
    private int timeCount;
    private int score;
    private int assignment;
    private int lastAssignment;
    private int delayFire;
    private boolean airJump;

    /**
     * Dafault constructer will assign defalut image (in this case it's images of Ma) to array list
     */
    public CharPlayer () {
        this("images/char_sheet/char_1/walk1_0.png",
             "images/char_sheet/char_1/walk1_1.png",
             "images/char_sheet/char_1/walk1_2.png",
             "images/char_sheet/char_1/walk1_3.png");
    }
    
    /**
     * Constructure with parameter is will get an array form sub-class. then assign to array-list
     * The coder can change button to jump and to fire with keyJunp and KeyFire variable's
     */
    public CharPlayer (String... img) {
        this.acceleration = 1;
        this.jumpStrength = 25;
        this.vSpeed       = 0;
        this.keyJump      = "space";
        this.keyFire      = "d";
        
        // Assign images to ArrayList call sprite and then, set the first image
        for (int i=0 ; i<img.length ; i++) {
            sprite.add(new GreenfootImage(img[i]));
        }
        setImage(sprite.get(imgCount));
    }
    
    /**
     * In act method, its usually do common method shuch as check object, increase score, increase time,
     * and has condition for jump. If character stand on the ground, they can jump, if not thier will fall
     */
    public void act() 
    {
        checkObject();
        updateCounter();
        increaseAssignment();
        checkKeyFire();
        timeKeeper();
        
        if ( onGround() ) {
            checkKeyJump();
            changeImages();
        }
        else {
            airJump();
            checkFall();
        }
        
        checkWorldEdge();
    }    
    
    /**
     * If player fall down to the hell, game will over
     */
    public void checkWorldEdge () {
        if (getY() > getWorld().getHeight()+10) {
            ((ScrollWorld)getWorld()).endGameLost();
        }
    }
    
    /**
     * Decrease cooldown to fire()
     */
    public void timeKeeper () {
        delayFire --;
    }
    
    /**
     * change image of character
     */
    public void changeImages () {
        if (timeCount++ % 9 == 0) {
            imgCount = (++imgCount)%4;
            setImage(sprite.get(imgCount));
        } 
    }
    
    /**
     * Assignment score will increase one when player get any 5 points
     */
    public void increaseAssignment () {
        if ((score - lastAssignment) >= 5) {
            assignment ++;
            lastAssignment = (score/5)*5;
        }
    }
    
    /**
     * Return assignment point
     */
    public int getAssignment() {
        return assignment;
    }
    
    /**
     * Return score point
     */
    public int getScore () {
        return score;
    }
    
    /**
     * If player press keyJump character will jump if they on the ground
     */
    private void checkKeyJump () {
        if ( Greenfoot.isKeyDown(keyJump) ) {
            if ( onGround()) {
                jump();
            }
        }
    }
    
    /**
     * If player press keyFire character will fire if they can
     */
    private void checkKeyFire () {
        // ready to shoot?
        if ( Greenfoot.isKeyDown(keyFire) && (delayFire<0) ) {
            fire();
        }
    }
    
    /**
     * First, character will check what location of enemy as its left or right handside
     * for example, enemy stand on right handside, character will send assignment to right handside.
     * 
     * In finally, assign 10 to delayFire
     */
    private void fire () {
        // first, method will find what location of the enemy, after that, shoot it
        ArrayList<CharBot> enemy = (ArrayList) getObjectsInRange(2000,CharBot.class);
        if ( enemy.size() > 0 && assignment > 0) {
            if ( enemy.get(0).getX() > getX() ) {
                getWorld().addObject(new HomeWork(5), getX()+10, getY());
                assignment --;
            }
            else if (  enemy.get(0).getX() < getX() ) {
                getWorld().addObject(new HomeWork(-5), getX()-10, getY());
                assignment --;
            }
        }
        delayFire = 10;
    }

    /**
     * If character jump, game will play sound effect and decrease jumpStrength of character (physical) then fall
     */
    private void jump() {
        Greenfoot.playSound("jump.mp3");
        setVSpeed(-jumpStrength);
        fall();
    }
    
    /**
     * Player can jump two time (we call that airJump).
     * the condition of airJump is player have to jump from only ground and jump for a little second
     */
    private void airJump() {
        if( Greenfoot.isKeyDown(keyJump) && airJump && (vSpeed>-5) ) {
            Greenfoot.playSound("jump.mp3");
            setVSpeed(-jumpStrength);
            airJump = !airJump;   // make it opposite to avoid jump many times in the air
        }
    }
    
    /**
     * Method for set gravity to jump
     */
    public void setVSpeed(int speed) {
        vSpeed = speed;
    }
    
    /**
     * Set location to fall 
     */
    public void fall() {
        setLocation ( getX(), getY() + vSpeed/3);
        vSpeed = vSpeed + acceleration;
    }
    
    /**
     * Check about character stand on ground or not if not they will fall
     */
    private void checkFall() {
        if (onGround()) {
            setVSpeed(0);
        }
        else {
            fall();
        }
    }
    
    /**
     * if character stand on ground (Ground class is super class for many ground) will return true
     */
    public boolean onGround() {
        Object under = getOneObjectAtOffset(0, getImage().getHeight()/2+8 , Ground.class);
        if (under != null) {
            airJump = !false;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Check item that was sent to character
     */
    public void checkObject () {
        Items item = (Items)getOneIntersectingObject(Items.class);
        if (item != null) {
            if (item instanceof Coins) {
                score = score + 2;
                Greenfoot.playSound("coin.mp3");
            }
            else if (item instanceof CoinsFall) {
                score = score + 1;
                Greenfoot.playSound("coin.mp3");
            }
            else if (item instanceof CoinCheat) {
                score = 999;
                assignment = 999;
                Greenfoot.playSound("coin.mp3");
            }
            getWorld().removeObject(item);
        }
    }
    
    /**
     * updateCounter method will call two metod for update any score to counter
     */
    public void updateCounter () {
        updateAssignment();
        updateScore();
    }
    public void updateAssignment () {
        if(assignmentCounter == null) {
             assignmentCounter = new Counter("HomeWork: ");
             getWorld().addObject(assignmentCounter, 75, 35);
        }
        else {
            assignmentCounter.update(assignment);
        }
    }
    public void updateScore () {
        if(scoreCounter == null) {
             scoreCounter = new Counter("Score: ");
             getWorld().addObject(scoreCounter, 60, 20);
        }
        else {
            scoreCounter.update(score);
        }
    }
}
