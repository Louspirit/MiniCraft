package fenetre;

import java.util.List;

import macro.Macro;

import util.Constant;

import de.lessvoid.nifty.*;
import de.lessvoid.nifty.screen.*;
import de.lessvoid.nifty.controls.*;
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
    private String texture = "Terre";
    private String mode = "Bloc";
    private String macroSelec = "";
     
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

    /**
     * Quitter le jeu
     * appelé par le bouton EXIT
     */
    public void quitGame(){
     	  game.stop();
     }
    
    /**
     * Applique tous les changements demandés dans le menu
     * type de forme
     * texture de bloc
     * si Macro choisie : quelle macro reproduire
     * appelé par le bouton CANCEL
     */
    public void appliquerChangements(){
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
     	 
     	 //Ferme le menu maintenant que tous les changements sont faits
     	 cancel();
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
}  