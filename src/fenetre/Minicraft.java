package fenetre;

import listener.BlocListener;
import listener.SettingListener;
import macro.MacroStore;
import ui.HUDControl;
import Player.IPlayerControl;
import Player.PlayerControl;

import World.BlockControl;
import World.IBlockControl;
import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Minicraft extends SimpleApplication implements ScreenController{
	
	private IMapControl mapControl;
	private IPlayerControl playerControl;
	// Gestion de la physique
	private BulletAppState bulletAppState;
	private Node map;
	private IBlockControl blockControl;
	private MacroStore macros;
	  
	private Nifty nifty;
    private Boolean MenuON=false;  
	 /** Defining the "Shoot" action: Determine what was hit and how to respond. */
	 private BlocListener blocListener;
	 // Listener pour les paramètres en cours (type de bloc, type de structure etc...)
	 private SettingListener settingListener;
	 private HUDControl hudControl;
	
	@Override
	public void simpleInitApp() {
		
	    initCrossHairs(); // a "+" in the middle of the screen to help aiming			    			    
		initCam();
        initHUD();

		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
	    
	    macros = new MacroStore();
		mapControl = new MapControl(this, bulletAppState, macros);
		macros.setMap(mapControl);
		map = mapControl.generateMap(32, 32, 4);
		rootNode.attachChild(map);	
		
		// Initialisation des listeners
		blockControl = new BlockControl(mapControl, this);
		
		
	    playerControl = new PlayerControl( cam);
	    blocListener = new BlocListener(cam, mapControl, blockControl, map);
	    settingListener = new SettingListener();
	    
	    setUpKeys();
	    
	    bulletAppState.getPhysicsSpace().add(playerControl.getPlayer());

	}
	
	private void initCam() {
		cam.setLocation(new Vector3f(8, 2, 8));
		cam.setFrustumPerspective(45, (float) cam.getWidth() / cam.getHeight(), 0.01f, 1000);
		flyCam.setMoveSpeed(10);
		//flyCam.setEnabled(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Minicraft minicraft = new Minicraft();
		minicraft.start();
	}

	/**
	 * Initialise la configuration des touches 
	 */
	private void setUpKeys() {
	    inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_Q));
	    inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
	    inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_Z));
	    inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
	    inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));

	    inputManager.addMapping("SwitchBlocUp", new MouseAxisTrigger(MouseInput.AXIS_WHEEL,false));
	    inputManager.addMapping("SwitchBlocDown", new MouseAxisTrigger(MouseInput.AXIS_WHEEL,true));
	    inputManager.addMapping("CreateForm", new KeyTrigger(KeyInput.KEY_F));
	    inputManager.addMapping("CreateFormFull", new KeyTrigger(KeyInput.KEY_G));
	    inputManager.addMapping("MacroRecStop", new KeyTrigger(KeyInput.KEY_M));
	    inputManager.addMapping("Add", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT)); 
	    inputManager.addMapping("Delete", new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); 
//	    inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
//	    inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
//	    inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
//	    inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));

	    inputManager.addMapping("Menu", new KeyTrigger(KeyInput.KEY_P));
	    
	    inputManager.addListener(playerControl, "Left", "Right", "Up", "Down", "Jump");
	    
	    inputManager.addListener(blocListener, "Add", "Delete");
	    inputManager.addListener(macros, "Add", "Delete", "MacroRecStop");
	    
	    inputManager.addListener(settingListener, "SwitchBlocUp", "SwitchBlocDown", "CreateForm", "CreateFormFull");
	    //inputManager.addListener(playerControl, "rotateLeft");
	    inputManager.addListener(OptionListener, "Menu");
	}

    @Override
    public void simpleUpdate(float tpf)
    {
        playerControl.walk();
    }
  
    /** A centred plus sign to help the player aim. */
    protected void initCrossHairs() {
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+"); // crosshairs
        ch.setLocalTranslation( // center
          settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
          settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }
    
    private void initHUD() {
        hudControl = new HUDControl(this, settings.getWidth(), settings.getHeight());
        guiNode.attachChild(hudControl.generatePictureBloc());
        guiNode.attachChild(hudControl.generatePictureForm());
    }
    
    private ActionListener OptionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
          if (name.equals("Menu") && !keyPressed) {      
          	if(!MenuON){
              NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,inputManager,
                                                                audioRenderer,guiViewPort);
              nifty = niftyDisplay.getNifty();
              
              nifty.fromXml("XML/Menu.xml", "start");

              // attach the nifty display to the gui view port as a processor
              guiViewPort.addProcessor(niftyDisplay);
              
              // disable the fly cam
              flyCam.setEnabled(false);
              //flyCam.setDragToRotate(false);
              MenuON = true;
  	        }else{
  	        	guiViewPort.clearProcessors();
  	        	flyCam.setEnabled(true);
  	            //flyCam.setDragToRotate(true);
  	        	
  	            MenuON = false;
  	        }
          }
       }
     };
      
      public void bind(Nifty nifty, Screen screen) {
          System.out.println("bind( " + screen.getScreenId() + ")");
      }

      public void onStartScreen() {
          System.out.println("onStartScreen");
      }

      public void onEndScreen() {
          System.out.println("onEndScreen");
      }

      public void quit(){
          System.out.println("quit");
      }
}


