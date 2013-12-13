import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

class Button2 extends StartButton {
    public Button2 () {
        setImage("selectScreen/screen1-button2.png");
    }
    
    protected void setAuto()
    {
        ((ScrollWorld)getWorld()).setAuto(false);
    }
}
