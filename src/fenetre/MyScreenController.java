package fenetre;

import de.lessvoid.nifty.*;
import de.lessvoid.nifty.screen.*;
import de.lessvoid.nifty.controls.*;

public class MyScreenController implements ScreenController
{   
    protected Minicraft game;
     
    public MyScreenController(Minicraft game)
    {
        this.game = game;
    }  
     
    public void bind(Nifty nifty, Screen screen)
    {       
    	System.out.println("bind");
    }
     
    public void onStartScreen()   
    {
    	System.out.println("onstart");
    }
     
    public void onEndScreen()
    {
    	System.out.println("onend");
    }   

    public void quitGame(){
     	  game.stop();
     }
}  