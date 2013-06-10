package fenetre;

import java.util.List;

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
    
    /**
     * This is called when the RadioButton selection has changed.
     */
    @NiftyEventSubscriber(id="type_forme")
    public void onRadioGroup1Changed1(final String id, final RadioButtonGroupStateChangedEvent event) {
      System.out.println("RadioButton [" + event.getSelectedId() + "] is now selected. The old selection was [" + event.getPreviousSelectedId() + "]");
    }
    
    /**
     * This is called when the RadioButton selection has changed.
     */
    @NiftyEventSubscriber(id="texture_bloc")
    public void onRadioGroup1Changed(final String id, final RadioButtonGroupStateChangedEvent event) {
      System.out.println("RadioButton [" + event.getSelectedId() + "] is now selected. The old selection was [" + event.getPreviousSelectedId() + "]");
    }
    
    /**
     * When the selection of the ListBox changes this method is called.
     */
    @NiftyEventSubscriber(id="myListBox")
    public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
      List<String> selection = event.getSelection();
      for (String selectedItem : selection) {
        System.out.println("listbox selection [" + selectedItem + "]");
      }
    }
}  