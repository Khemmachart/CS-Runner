import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot) 

/**
 * A class that has a scrolling background image
 * 
 * @author Poul Henriksen
 */
public class ScrollWorld extends World 
{ 
    //private static final GreenfootImage bgImage7 = new GreenfootImage("/bg/city0.gif");
    private static final GreenfootImage bgImage7 = new GreenfootImage("/bg/bg.gif");
    private static final GreenfootImage bgImage6 = new GreenfootImage("/bg/cloud.gif");
    private static final GreenfootImage bgImage5 = new GreenfootImage("/bg/city4.gif");
    private static final GreenfootImage bgImage4 = new GreenfootImage("/bg/city3.gif");
    private static final GreenfootImage bgImage3 = new GreenfootImage("/bg/city2.gif");
    private static final GreenfootImage bgImage2 = new GreenfootImage("/bg/city1.gif");
    private static final GreenfootImage bgImage1 = new GreenfootImage("/bg/front.gif");

    private GreenfootImage scrollingImage7;
    private GreenfootImage scrollingImage6;
    private GreenfootImage scrollingImage5;
    private GreenfootImage scrollingImage4;
    private GreenfootImage scrollingImage3;
    private GreenfootImage scrollingImage2;
    private GreenfootImage scrollingImage1;
    
    private GreenfootSound soundBGM;
   
    private double scrollPosition7 = 0;
    private double scrollPosition6 = 0;
    private double scrollPosition5 = 0;
    private double scrollPosition4 = 0;
    private double scrollPosition3 = 0;
    private double scrollPosition2 = 0;
    private double scrollPosition1 = 0;
    
    private double speed7 = 0;
    private double speed6 = 0.25;
    private double speed5 = 0.75;
    private double speed4 = 1.25;
    private double speed3 = 1.75;
    private double speed2 = 2.25;
    private double speed1 = 2.75;
    
    private CharBot AI = new Ajarn(true);
    private CharPlayer player = new Player00();
    
    private int ground = 1;
    private int groundCreated = 3;
    private int time = 1;
    private int location = 40;
    private boolean start;
    private boolean auto;
    private boolean sound = false;
     
    public ScrollWorld()
    {
        super(1000, 500, 1, false);
        
        GreenfootImage background7 = new GreenfootImage(1000, 500);
        GreenfootImage background6 = new GreenfootImage(1000, 500);
        GreenfootImage background5 = new GreenfootImage(1000, 500);
        GreenfootImage background4 = new GreenfootImage(1000, 500);
        GreenfootImage background3 = new GreenfootImage(1000, 500);
        GreenfootImage background2 = new GreenfootImage(1000, 500);
        GreenfootImage background1 = new GreenfootImage(1000, 500);
       
        scrollingImage7 = getScrollingImage7(1000, 500);
        scrollingImage6 = getScrollingImage6(1000, 500);
        scrollingImage5 = getScrollingImage5(1000, 500);
        scrollingImage4 = getScrollingImage4(1000, 500);
        scrollingImage3 = getScrollingImage3(1000, 500);
        scrollingImage2 = getScrollingImage2(1000, 500);
        scrollingImage1 = getScrollingImage1(1000, 500);
       
        background7.drawImage(scrollingImage7, 0, 0);
        background6.drawImage(scrollingImage6, 0, 0);
        background5.drawImage(scrollingImage5, 0, 0);
        background4.drawImage(scrollingImage4, 0, 0);
        background3.drawImage(scrollingImage3, 0, 0);
        background2.drawImage(scrollingImage2, 0, 0);
        background1.drawImage(scrollingImage1, 0, 0);
        
        setBackground(background7);
        setBackground(background6);
        setBackground(background5);
        setBackground(background4);
        setBackground(background3);
        setBackground(background2);
        setBackground(background1);

        // Show the screen for player select mode
        selectionScreen();
    }
    
    /**
     * In selectionScreen method that will create menu for selection, to play the game
     */
    public void selectionScreen () {
        setBackground(new GreenfootImage("images/selectScreen/screen1.png"));
        
        addObject(new Butt00(), 127 ,279);    // Button of Ma (number 23)
        addObject(new Butt05(), 255 ,287);    // Button of Khaw (number 05)
        addObject(new Butt34(), 377 ,285);    // Button of Aom (number 34)
        addObject(new Butt41(), 510 ,269);    // Button of Appo (number 41)
        
        addObject(new ButtAjarn(), 742, 285); // Button of Ajarn
        addObject(new ButtPjiib(), 865, 285); // Button of Pjiib
        
        addObject(new Button1(), 500, 400);   // Start Button of Player vs Player
        addObject(new Button2(), 500, 435);   // Start Button of Player vs Computer
    }
    
    public void act()
    {
        // First, create a music if it is null, then play it with loop util player exit the game
        if (soundBGM != null) {
             soundBGM.playLoop();
        }
        else {
            soundBGM = new GreenfootSound("BGM.wav");
        }
        
        // The start variable's will become true when Player select game mode and then, game will start
        if (start) {
            timeCounter();
            // ground will create in the game by drawGroundEvent();
            drawGroundEvent();
            
            // scrollPosition1 to scrollPosition7 are represent layers of background
            scrollPosition7();
            scrollPosition6();
            scrollPosition5();
            scrollPosition4();
            scrollPosition3();
            scrollPosition2();
            scrollPosition1();
        }
    }
    
    /**
     * First, we have to remove all object (in selection screen) then create any object in the game
     */
    public void startGame () {
        // remove selection screen
        removeAllObjects();
        drawFirstGround();
        // set mode
        AI.setAuto(auto);
        // create characters
        addObject(AI, 800, 200);
        addObject(player, 350, 200);
        // make it to start the game
        start = true;
    }
    
    /**
     * First, we must stop the game by assign false to start varisble's then we have to remove all object (in play game mode) then show image win
     */
    public void endGameWon () {
        start = false;
        removeAllObjects();
        setBackground(new GreenfootImage("images/selectScreen/won.png"));
    }
    
    /**
     * First, we must stop the game by assign false to start varisble's then we have to remove all object (in play game mode) then show image lost
     */
    public void endGameLost () {
        start = false;
        removeAllObjects();
        setBackground(new GreenfootImage("images/selectScreen/lost.png"));
    }
    
    /**
     * Set mode of the game, if auto is true that mean play with computer
     */
    public void setAuto (boolean auto) {
        this.auto = auto;
    }
    
    /**
     * There are method for create and characters
     */
    public void createPlayer00 () {
        player = new Player00();
    }
    public void createPlayer05 () {
        player = new Player05();
    }
    public void createPlayer34 () {
        player = new Player34();
    }
    public void createPlayer41 () {
        player = new Player41();
    }
    public void createAjarn () {
        AI = new Ajarn(auto);
    }
    public void createPjiib () {
        AI = new Pjiib(auto);
    }
    
    /**
     * There are method for slide background
     */
    public void scrollPosition7 () {
        if(speed7 > 0 && scrollPosition7 <= 0) {
            scrollPosition7 = getWidth();
        }
        if(speed7 < 0 && scrollPosition7 >= getWidth()) {
            scrollPosition7 = 0;
        }
        scrollPosition7 = scrollPosition7 - speed7;
        paint7((int)scrollPosition7);
    }
    public void scrollPosition6 () {
        if(speed6 > 0 && scrollPosition6 <= 0) {
            scrollPosition6 = getWidth();
        }
        if(speed6 < 0 && scrollPosition6 >= getWidth()) {
            scrollPosition6 = 0;
        }
        scrollPosition6 = scrollPosition6 - speed6;
        paint6((int)scrollPosition6);
    }
    public void scrollPosition5 () {
        if(speed5 > 0 && scrollPosition5 <= 0) {
            scrollPosition5 = getWidth();
        }
        if(speed5 < 0 && scrollPosition5 >= getWidth()) {
            scrollPosition5 = 0;
        }
        scrollPosition5 = scrollPosition5 - speed5;
        paint5((int)scrollPosition5);
    }
     public void scrollPosition4 () {
        if(speed4 > 0 && scrollPosition4 <= 0) {
            scrollPosition4 = getWidth();
        }
        if(speed4 < 0 && scrollPosition4 >= getWidth()) {
            scrollPosition4 = 0;
        }
        scrollPosition4 = scrollPosition4 - speed4;
        paint4((int)scrollPosition4);
    }
     public void scrollPosition3 () {
        if(speed3 > 0 && scrollPosition3 <= 0) {
            scrollPosition3 = getWidth();
        }
        if(speed3 < 0 && scrollPosition3 >= getWidth()) {
            scrollPosition3 = 0;
        }
        scrollPosition3 = scrollPosition3 - speed3;
        paint3((int)scrollPosition3);
    }
    public void scrollPosition2 () {
        if(speed2 > 0 && scrollPosition2 <= 0) {
            scrollPosition2 = getWidth();
        }
        if(speed2 < 0 && scrollPosition2 >= getWidth()) {
            scrollPosition2 = 0;
        }
        scrollPosition2 = scrollPosition2 - speed2;
        paint2((int)scrollPosition2);
    }
    public void scrollPosition1 () {
        if(speed1 > 0 && scrollPosition1 <= 0) {
            scrollPosition1 = getWidth();
        }
        if(speed1 < 0 && scrollPosition1 >= getWidth()) {
            scrollPosition1 = 0;
        }
        scrollPosition1 = scrollPosition1 - speed1;
        paint1((int)scrollPosition1);
    }
    
    /**
     * Paint scrolling image at given position and make sure the rest of
     * the background is also painted with the same image.
     */
    private void paint7 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage7, position, 0);
        bg.drawImage(scrollingImage7, position - scrollingImage7.getWidth(), 0);
    }
    private void paint6 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage6, position, 0);
        bg.drawImage(scrollingImage6, position - scrollingImage6.getWidth(), 0);
    }
    private void paint5 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage5, position, 0);
        bg.drawImage(scrollingImage5, position - scrollingImage5.getWidth(), 0);
    }
    private void paint4 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage4, position, 0);
        bg.drawImage(scrollingImage4, position - scrollingImage4.getWidth(), 0);
    }
    private void paint3 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage3, position, 0);
        bg.drawImage(scrollingImage3, position - scrollingImage3.getWidth(), 0);
    }
    private void paint2 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage2, position, 0);
        bg.drawImage(scrollingImage2, position - scrollingImage2.getWidth(), 0);
    }
    private void paint1 (int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(scrollingImage1, position, 0);
        bg.drawImage(scrollingImage1, position - scrollingImage1.getWidth(), 0);
    }
    
    /**
     * Returns an image with the given dimensions.
     */
    private GreenfootImage getScrollingImage7(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage7.getWidth()) {
            for(int y = 0; y < height; y += bgImage7.getHeight()) {
                image.drawImage(bgImage7, x, y);
            }
        }
        return image;
    } 
    private GreenfootImage getScrollingImage6(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage6.getWidth()) {
            for(int y = 0; y < height; y += bgImage6.getHeight()) {
                image.drawImage(bgImage6, x, y);
            }
        }
        return image;
    } 
    private GreenfootImage getScrollingImage5(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage5.getWidth()) {
            for(int y = 0; y < height; y += bgImage5.getHeight()) {
                image.drawImage(bgImage5, x, y);
            }
        }
        return image;
    } 
    private GreenfootImage getScrollingImage4(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage4.getWidth()) {
            for(int y = 0; y < height; y += bgImage4.getHeight()) {
                image.drawImage(bgImage4, x, y);
            }
        }
        return image;
    } 
    private GreenfootImage getScrollingImage3(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage3.getWidth()) {
            for(int y = 0; y < height; y += bgImage3.getHeight()) {
                image.drawImage(bgImage3, x, y);
            }
        }
        return image;
    } 
    private GreenfootImage getScrollingImage2(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage2.getWidth()) {
            for(int y = 0; y < height; y += bgImage2.getHeight()) {
                image.drawImage(bgImage2, x, y);
            }
        }
        return image;
    }
    private GreenfootImage getScrollingImage1(int width, int height)
    {
        GreenfootImage image = new GreenfootImage(width, height);
        for(int x = 0; x < width; x += bgImage1.getWidth()) {
            for(int y = 0; y < height; y += bgImage1.getHeight()) {
                image.drawImage(bgImage1, x, y);
            }
        }
        return image;
    } 
    
    /**
     * Just return a time
     */
    public int getCurrentTime () {
        return this.time;
    }
    
    /**
     * drawFirstGround, its mean FirstGround for character stand on when the game start
     */
    private void drawFirstGround () {
        // draw the ground when game begin, if not, character will haven't place to stand
        for (int i=0 ; i<(getWidth()/110)*110 ; i+=110) {
            addObject(new GroundMiddle(),i,getHeight()-40);
        }
        addObject(new GroundLast(),(getWidth()/110)*110, getHeight()-40);
    }
    
    /**
     * Random height or low of the ground
     */
    public void randLocation () {
        // random height of location for the ground
        int randLocation = Greenfoot.getRandomNumber(2);
        if (randLocation == 0) {
            location =  40;
        }
        else if (randLocation == 1){
            location =  100;
        }
    }
    
    public void makeCoinsFall () {
        if (time % 15 == 0)
        addObject(new CoinsFall(),getWidth()-90,50);
    }
    
    public void makeCoins () {
        if (time % 15 == 0)
        addObject(new Coins(), getWidth()+30, getHeight()-location-250);
    }
    
    /**
     * drawGroundEvent
     * We have to random time to defind length of ground, if time less than 50 its will not generate ground (Player have to jump)
     * then if time more than 50, game will generate ground in the proper location that was random
     * 
     * at the same time, that method will check what ground that should generate. For example, FirstGround will generate first, then MiddleGround will generate
     * until that last time, and then will generate LastGround
     * 
     * at the same time too, many coins will create, GoldCoins will create when time less than 50, and SilverCoins will create when time more than 50
     * 
     * Finally, if time up to time that was created, method will resused (every variable will reset and random new time)
     */
    
    private void drawGroundEvent () {
        if (time > 50) {
            // make silver coins
            makeCoinsFall();
            if ( ground <= groundCreated && time % (110/Ground.SPEED) == 0) {  
                // time to make a ground is ground width (110) divided by speed of slide
                if (ground == 1) {
                    addObject(new GroundFirst(), getWidth()+50, getHeight()-location);
                }
                else if (ground == groundCreated-1) {
                    addObject(new GroundLast(), getWidth()+50, getHeight()-location);
                }
                else {
                    addObject(new GroundMiddle(), getWidth()+50, getHeight()-location);
                }
                ground ++;
            }
            else if (ground >= groundCreated) {
                // groundCreated will tell you, how far the ground place 
                // lessest value is 3 for first, middle, and last ground
                groundCreated = 3 + Greenfoot.getRandomNumber(5);
                ground        = 1;
                time          = 1;
                randLocation();
            }
        }
        else {
            // make gold coins
            makeCoins();
        }
    }
    
    public void timeCounter () {
        time ++;
    }
    
    private void removeAllObjects () {
        removeObjects (getObjects(Actor.class));
    }
}
