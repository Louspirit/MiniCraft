package fenetre;

import java.util.List;

import listener.SettingListener;
import macro.Macro;

import util.Constant;

import de.lessvoid.nifty.*;
import de.lessvoid.nifty.screen.*;
import de.lessvoid.nifty.controls.*;
import de.lessvoid.nifty.elements.Element;

import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.renderer.ViewPort;

/**
 * Classe controllant le menu de Minicraft
 * Repercute les configurations du joueurs
 * @author Guillaume
 *
 */
public class MyScreenController implements ScreenController
{   
    protected Minicraft game;
    private String texture = "";
    private String mode = "";
    private String macroSelec = "";
    Element exitPopup;
    Element noMacroPopup;
    
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
    	checkRadioButton(game.getMenuListener().getNiftyDisplay().getNifty().getCurrentScreen());
    	fillMyListBox(game.getMenuListener().getNiftyDisplay().getNifty().getCurrentScreen());
    	System.out.println("onstart");
    }
     
    public void onEndScreen()
    {
    	System.out.println("onend");
    }   

    /**
     * Quitter le jeu
     * appelé par le bouton EXIT
     */
    public void quitGame(){
    	//Balancer une popup "pas de macro disponible"
		 	Nifty nifty = game.getMenuListener().getNiftyDisplay().getNifty();
		 	exitPopup = nifty.createPopup("exitPopup");
			nifty.showPopup(nifty.getCurrentScreen(), exitPopup.getId(), null);
     }
    
    @NiftyEventSubscriber(id = "BtYes")
    public void BtYesClicked(final String id, final ButtonClickedEvent event) {
        game.stop();
    }
    
    @NiftyEventSubscriber(id = "Accept")
    public void BtAcceptClicked(final String id, final ButtonClickedEvent event) {
    	Nifty nifty = game.getMenuListener().getNiftyDisplay().getNifty();
	 	nifty.closePopup(noMacroPopup.getId());
    }
    
    @NiftyEventSubscriber(id = "BtNo")
    public void BtNoClicked(final String id, final ButtonClickedEvent event) {
    	Nifty nifty = game.getMenuListener().getNiftyDisplay().getNifty();
	 	nifty.closePopup(exitPopup.getId());
   }
    
    /**
     * Applique tous les changements demandés dans le menu
     * type de forme
     * texture de bloc
     * si Macro choisie : quelle macro reproduire
     * appelé par le bouton CANCEL
     */
    public void appliquerChangements(){
    	boolean erratum = false;
     	  // MAJ de la texture des blocs
		 if("Terre".equals(texture)){
     		  game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Terre);
     	  }
     	 if("Herbe".equals(texture)){
    		  game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Herbe);
    	  }
     	 if("Beton".equals(texture)){
     		 game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Beton);
     	 }	
     	 if("Eau".equals(texture)){
     		 game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Eau);
     	 }
     	 if("Chiot".equals(texture)){
     		 game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Chiot);
     	 }
     	 if("Bois".equals(texture)){
    		 game.getSettingListener().getSetting().setTypeBloc(Constant.Bloc_Bois);
    	 }
     	 
     	 //MAJ de la forme des blocs
     	if("Bloc".equals(mode)){
   		  	game.getSettingListener().getSetting().setMode(Constant.Bloc);
	   	  }
	   	 if("Rectangle".equals(mode)){
	  		 game.getSettingListener().getSetting().setMode(Constant.Form);
	  	  }
	   	 if("Macro".equals(mode)){
	   		 //On va chercher la macro selectionnée dans la liste des macros
	   		 if("".equals(macroSelec)){
	   			 //Balancer une popup "pas de macro disponible"
	   			 	Nifty nifty = game.getMenuListener().getNiftyDisplay().getNifty();
					noMacroPopup = nifty.createPopup("noMacroPopup");
					nifty.showPopup(nifty.getCurrentScreen(), noMacroPopup.getId(), null);
					erratum = true;
	   		 }else{
	   			game.getSettingListener().getSetting().setMode(Constant.Macro);
	   			int indexMacro = 0;
	   			List<Macro> listeM = game.getMacroStore().getListe();
	   			for(Macro mac : listeM){
	   				if( macroSelec.equals(mac.getNom())){
	   					indexMacro = listeM.indexOf(mac); 
	   				}
	   			}
	   			 game.getMacroStore().choisirMacro(indexMacro);
	   		 }
	   	 }	
     	 if(!erratum){
	     	 //Ferme le menu maintenant que tous les changements sont faits
	     	 cancel();
     	 }
     }
    
    /**
     * Ferme le menu sans rien modifié
     * appelé par le bouton CANCEL, revient au même que de rappuyer sur le bouton pause
     */
    public void cancel(){
    	Minicraft minicraft = Minicraft.getInstance();
    	ViewPort guiViewPort = minicraft.getGuiViewPort();
		FlyByCamera flyCam = minicraft.getFlyByCamera();
		InputManager inputManager = minicraft.getInputManager();
		guiViewPort.clearProcessors();
      	flyCam.setEnabled(true);
      	inputManager.setCursorVisible(false);
      	minicraft.showCrosshair();
      	minicraft.setUpKeys();
      	minicraft.getMenuListener().setMenuON(false);
     }
    
    /**
     * This is called when the RadioButton type_forme selection has changed.
     */
    @NiftyEventSubscriber(id="type_forme")
    public void onRadioGroup1Changed1(final String id, final RadioButtonGroupStateChangedEvent event) {
      mode = event.getSelectedId();
    }
    
    /**
     * This is called when the RadioButton texture_bloc selection has changed.
     */
    @NiftyEventSubscriber(id="texture_bloc")
    public void onRadioGroup1Changed(final String id, final RadioButtonGroupStateChangedEvent event) {
      texture = event.getSelectedId();
    }
    
    /**
     * When the selection of the ListBox changes this method is called.
     */
    @NiftyEventSubscriber(id="myListBox")
    public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
      List<String> selection = event.getSelection();
      macroSelec = event.getSelection().get(0);
    }
    
	/**
	 * Fill the listbox with items. In this case with Strings.
	 */
    public void fillMyListBox(Screen screen) {
      ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
      List<Macro> macros = game.getMacroStore().getListe();
      for(Macro macro : macros){
     listBox.addItem(macro.getNom());
      }
    }
    
    /**
	 * Fill the listbox with items. In this case with Strings.
	 */
    public void checkRadioButton(Screen screen) {
    	String forme = "";
    	String mode = game.getSettingListener().getSetting().getModeOnly();
		if(Constant.Bloc.equals(mode)){
     		  forme = "Bloc";
     	}
		else if(Constant.Form.equals(mode)){
   		  	forme = "Rectangle";
   	  	}
		else if(Constant.FormFull.equals(mode)){
	   		  forme = "Rectangle";
	   	}else if(Constant.Macro.equals(mode)){
	   		  forme = "Rectangle";
	   	}
		RadioButton radiobuttonf = screen.findNiftyControl(forme , RadioButton.class); 
		radiobuttonf.select();
		String texture = game.getSettingListener().getSetting().getTypeBloc().toString();
		RadioButton radiobuttont = screen.findNiftyControl(texture , RadioButton.class); 
		radiobuttont.select();
    }
}  