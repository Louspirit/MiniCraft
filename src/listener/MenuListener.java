package listener;

import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import fenetre.Minicraft;

public class MenuListener implements ActionListener {
	private Boolean MenuON=false;
	private NiftyJmeDisplay niftyDisplay;
	
	public MenuListener(NiftyJmeDisplay niftyDisplay) {
		this.niftyDisplay = niftyDisplay;
	}
	
	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
		if (name.equals("Menu") && !keyPressed) {
          	ViewPort guiViewPort = Minicraft.getInstance().getGuiViewPort();
			FlyByCamera flyCam = Minicraft.getInstance().getFlyByCamera();
			InputManager inputManager = Minicraft.getInstance().getInputManager();
			
			if (!MenuON) {
				// attach the nifty display to the gui view port as a processor
				guiViewPort.addProcessor(niftyDisplay);
				niftyDisplay.getNifty().fromXml("XML/Menu.xml", "start");
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
				
				Minicraft.getInstance().hideCrosshair();
				
				MenuON = true;
  	        }else{
  	        	guiViewPort.clearProcessors();
  	        	flyCam.setEnabled(true);
  	            //flyCam.setDragToRotate(true);
  	        	inputManager.setCursorVisible(false);
  	        	
  	        	 Minicraft.getInstance().setUpKeys();
  	        	 
  	        	Minicraft.getInstance().showCrosshair();
  	        	
  	            MenuON = false;
  	        }
          }
	}

}
