package listener;

import java.util.ArrayList;
import java.util.List;

import macro.Macro;
import macro.MacroStore;

import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;

import fenetre.Minicraft;
import fenetre.MyScreenController;

/**
 * Permet de lancer la création menu de Minicraft, dans lequel on peut configurer les types de contruction voulues 
 * @author Guillaume
 *
 */
public class MenuListener implements ActionListener {
	private Boolean MenuON=false;
	private NiftyJmeDisplay niftyDisplay;
	private MyScreenController controller ;
	private MacroStore macroStore;
	public MenuListener(NiftyJmeDisplay niftyDisplay, MyScreenController menuController,MacroStore macroStore) {
		this.niftyDisplay = niftyDisplay;
		this.controller = menuController;
		this.macroStore = macroStore;
	}
	
	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
		if (name.equals("Menu") && !keyPressed) {
			Minicraft minicraft = Minicraft.getInstance();
          	ViewPort guiViewPort = minicraft.getGuiViewPort();
			FlyByCamera flyCam = minicraft.getFlyByCamera();
			InputManager inputManager = minicraft.getInputManager();
			if (!MenuON) {
				// attach the nifty display to the gui view port as a processor
				guiViewPort.addProcessor(niftyDisplay);
				niftyDisplay.getNifty().fromXml("XML/Menu.xml", "start",controller);
				fillMyListBox(niftyDisplay.getNifty().getCurrentScreen());
				// disable the fly cam
				flyCam.setEnabled(false);
				//flyCam.setDragToRotate(false);
				inputManager.deleteMapping("Left");
				inputManager.deleteMapping("Right");
				inputManager.deleteMapping("Up");
				inputManager.deleteMapping("Down");
				inputManager.deleteMapping("Jump");
				inputManager.deleteMapping("SwitchBlocUp");
				inputManager.deleteMapping("SwitchBlocDown");
				inputManager.deleteMapping("CreateForm");
				inputManager.deleteMapping("CreateFormFull");
				inputManager.deleteMapping("Add");
				inputManager.deleteMapping("Delete");
				
				minicraft.hideCrosshair();
				
				MenuON = true;
  	        }else{
  	        	guiViewPort.clearProcessors();
  	        	flyCam.setEnabled(true);
  	            //flyCam.setDragToRotate(true);
  	        	inputManager.setCursorVisible(false);
  	        	minicraft.showCrosshair();
  	        	minicraft.setUpKeys();
  	        	MenuON = false;
  	        }
          }
	}
	
	/**
	 * @return the niftyDisplay
	 */
	public NiftyJmeDisplay getNiftyDisplay() {
		return niftyDisplay;
	}

	/**
	 * @param menuON the menuON to set
	 */
	public void setMenuON(Boolean menuON) {
		MenuON = menuON;
	}

	/**
     * Fill the listbox with items. In this case with Strings.
     */
    public void fillMyListBox(Screen screen) {
      ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
      List<Macro> macros = macroStore.getListe();
      for(Macro macro : macros){
    	  listBox.addItem(macro.getNom());
      }
    
    }

}
