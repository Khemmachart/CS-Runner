import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class StartButton extends Button {    
    public void act () {
        doClick();
    }
    
    private void doClick()
    {
        if (Greenfoot.mouseClicked(this)) {
            setAuto();
            ((ScrollWorld)getWorld()).startGame();
        }
    }
    
    abstract protected void setAuto ();
}
