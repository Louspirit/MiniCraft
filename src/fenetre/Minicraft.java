package fenetre;

import listener.BlocListener;
import listener.MenuListener;
import listener.SettingListener;
import macro.MacroStore;
import ui.HUDControl;
import Player.IPlayerControl;
import Player.PlayerControl;
import Player.PlayerSettingChoice;

import World.BlockControl;
import World.IBlockControl;
import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
/**
 * Classe principale du jeu Minicraft , un MineCraft-like permettant d'enregistrer des macros et de les rejouer, 
 * de créer de grandes surfaces grâce au rectangle à dimension variable
 * @author Guillaume E., Victor R., Benjamin B., Anthony O.
 *
 */
public class Minicraft extends SimpleApplication{
	

	private static Minicraft instance;
	private IMapControl mapControl;
	private IPlayerControl playerControl;
	private PlayerSettingChoice settingPlayer = PlayerSettingChoice.getInstance();
	// Gestion de la physique
	private BulletAppState bulletAppState;
	private Node map;
	private MyScreenController controller = new MyScreenController(this);
	private IBlockControl blockControl;
	private MacroStore macros;
	  
	private Nifty nifty;
    
	 /** Defining the "Shoot" action: Determine what was hit and how to respond. */
	 private BlocListener blocListener;
	 // Listener pour les paramètres en cours (type de bloc, type de structure etc...)
	 private SettingListener settingListener;
	 private HUDControl hudControl;
	 private MenuListener menuListener;
	 
	 private BitmapText crosshair;
	
	 private Minicraft(){}
	 
	@Override
	public void simpleInitApp() {
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
		nifty = niftyDisplay.getNifty();
		 
	    initCrossHairs(); // a "+" in the middle of the screen to help aiming			    			    
		initCam();
        initHUD();
        initSky();
        
		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
	    
	    macros = new MacroStore();
		mapControl = new MapControl(bulletAppState, macros);
		macros.setMap(mapControl);
		map = mapControl.generateMap(32, 32, 4);
		rootNode.attachChild(map);
		
		blockControl = new BlockControl(mapControl);
	    playerControl = new PlayerControl( cam);
	    
	    // Initialisation des listeners
	    blocListener = new BlocListener(mapControl, blockControl);
	    settingListener = new SettingListener();
	    menuListener = new MenuListener(niftyDisplay,controller,macros,settingListener);
	    
	    setUpKeys();
	    
	    bulletAppState.getPhysicsSpace().add(playerControl.getPlayer());
	    
	    // On crée une pyramide
	    for (int i=0; i <= 6 ; i++ ) {
	    	settingPlayer.setTypeBloc(3); // le bois
		    blockControl.createRectangle(new Vector3f(20+i,5+i,20+i), new Vector3f(32-i,5+i,32-i), true);
	    	settingPlayer.setTypeBloc(0); // la terre
	    }
	}
	
	private void initSky() {
		rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false));
	}

	private void initCam() {
		cam.setLocation(new Vector3f(8, 2, 8));
		cam.setFrustumPerspective(45, (float) cam.getWidth() / cam.getHeight(), 0.01f, 1000);
		flyCam.setMoveSpeed(10);
		//flyCam.setEnabled(false);
	}

	public static Minicraft getInstance()
	{
		if(Minicraft.instance == null) 
		{
			Minicraft.instance = new Minicraft();
		}
		return instance;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Minicraft.getInstance().start();
	}

	/**
	 * Initialise la configuration des touches 
	 */
	public void setUpKeys() {
	    inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_Q));
	    inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
	    inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_Z));
	    inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
	    inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));

	    inputManager.addMapping("SwitchBlocUp", new MouseAxisTrigger(MouseInput.AXIS_WHEEL,false));
	    inputManager.addMapping("SwitchBlocDown", new MouseAxisTrigger(MouseInput.AXIS_WHEEL,true));
	    inputManager.addMapping("CreateForm", new KeyTrigger(KeyInput.KEY_F));
	    inputManager.addMapping("CreateFormFull", new KeyTrigger(KeyInput.KEY_G));
	    inputManager.addMapping("MacroRecStop", new KeyTrigger(KeyInput.KEY_E));
	    inputManager.addMapping("PreviousMacro", new KeyTrigger(KeyInput.KEY_R));
	    inputManager.addMapping("NextMacro", new KeyTrigger(KeyInput.KEY_T));
	    inputManager.addMapping("PlayMacro", new KeyTrigger(KeyInput.KEY_Y));
	    
	    
	    inputManager.addMapping("Add", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT)); 
	    inputManager.addMapping("Delete", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

	    inputManager.addMapping("Menu", new KeyTrigger(KeyInput.KEY_P));
	    
	    inputManager.addListener(playerControl, "Left", "Right", "Up", "Down", "Jump");
	    
	    inputManager.addListener(blocListener, "Add", "Delete");
	    inputManager.addListener(macros, "Add", "Delete", "MacroRecStop", "NextMacro", "PreviousMacro", "PlayMacro");
	    
	    inputManager.addListener(settingListener, "SwitchBlocUp", "SwitchBlocDown", "CreateForm", "CreateFormFull");
	    inputManager.addListener(menuListener, "Menu");
	}

    @Override
    public void simpleUpdate(float tpf)
    {
        playerControl.walk();
        
        // delete default flycam wheel mapping
        if (inputManager.hasMapping("FLYCAM_ZoomIn")) {
            inputManager.deleteMapping("FLYCAM_ZoomIn");
        }
        if (inputManager.hasMapping("FLYCAM_ZoomOut")) {
            inputManager.deleteMapping("FLYCAM_ZoomOut");
        }
    }
  
    /** A centred plus sign to help the player aim. */
    protected void initCrossHairs() {
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        crosshair = new BitmapText(guiFont, false);
        crosshair.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        crosshair.setText("+"); // crosshairs
        crosshair.setLocalTranslation( // center
          settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
          settings.getHeight() / 2 + crosshair.getLineHeight() / 2, 0);
        showCrosshair();
    }
    
    public void showCrosshair() {
    	guiNode.attachChild(crosshair);
    }
    
    public void hideCrosshair() {
    	guiNode.detachChild(crosshair);
    }
    
    private void initHUD() {
    	setDisplayFps(false); // to hide the FPS
    	setDisplayStatView(false); // to hide the statistics 
    	
        hudControl = new HUDControl(settings.getWidth(), settings.getHeight());
        guiNode.attachChild(hudControl.generatePictureBloc());
        guiNode.attachChild(hudControl.generatePictureForm());
    }
  	public BulletAppState getBulletAppState() {
		return bulletAppState;
	}

	public Node getMap() {
		return map;
	}
	

	/**
	 * @return the blocListener
	 */
	public BlocListener getBlocListener() {
		return blocListener;
	}

	/**
	 * @return the settingListener
	 */
	public SettingListener getSettingListener() {
		return settingListener;
	}

	/**
	 * @return the menuListener
	 */
	public MenuListener getMenuListener() {
		return menuListener;
	}
	
	
	/**
	 * @return the macroStore
	 */
	public MacroStore getMacroStore() {
		return macros;
	}
}


