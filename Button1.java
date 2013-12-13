import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
class Button1 extends StartButton{
    public Button1 () {
        setImage("selectScreen/screen1-button1.png");
    }
    
    protected void setAuto()
    {
        ((ScrollWorld)getWorld()).setAuto(true);
    }
}
