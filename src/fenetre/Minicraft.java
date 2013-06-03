package fenetre;

import listener.BlocListener;
import listener.SettingListener;
import util.Constant;
import Player.BetterPlayerControl;
import Player.IPlayerControl;
import Player.PlayerControl;
import Player.PlayerSettingChoice;

import World.Block;
import World.BlockControl;
import World.IBlockControl;
import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.bullet.BulletAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

public class Minicraft extends SimpleApplication {
	
	private IMapControl mapControl;
	private IPlayerControl playerControl;
	// Gestion de la physique
	private BulletAppState bulletAppState;
	private Node map;
	private IBlockControl blockControl;
	  
	 /** Defining the "Shoot" action: Determine what was hit and how to respond. */
	 private BlocListener actionListener;
	 // Listener pour les paramètres en cours (type de bloc, type de structure etc...)
	 private SettingListener settingListener;
	
	@Override
	public void simpleInitApp() {
		
	    initCrossHairs(); // a "+" in the middle of the screen to help aiming			    			    
		initCam();	
		PlayerSettingChoice.init();

		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
		
		mapControl = new MapControl();
		mapControl.init(this, bulletAppState);
		flyCam.setMoveSpeed(10);
		Node map = mapControl.generateMap(16, 16, 1);
		rootNode.attachChild(map);	
		
		blockControl = new BlockControl(mapControl, this);
		
	    playerControl = new PlayerControl( cam);
	    actionListener = new BlocListener(cam, mapControl, blockControl, map);
	    settingListener = new SettingListener();
	    setUpKeys();
	    
	    bulletAppState.getPhysicsSpace().add(playerControl.getPlayer());
	    

	}
	
	private void initCam() {
		cam.setLocation(new Vector3f(8, 2, 8));
		cam.setFrustumPerspective(45, (float) cam.getWidth() / cam.getHeight(), 0.01f, 1000);
		flyCam.setMoveSpeed(40);
		//flyCam.setEnabled(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	    inputManager.addMapping("Add", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT)); 
	    inputManager.addMapping("Delete", new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); 
//	    inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
//	    inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
//	    inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
//	    inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
	    
	    inputManager.addListener(playerControl, "Left");
	    inputManager.addListener(playerControl, "Right");
	    inputManager.addListener(playerControl, "Up");
	    inputManager.addListener(playerControl, "Down");
	    inputManager.addListener(playerControl, "Jump");
	    
	    inputManager.addListener(actionListener, "Add");
	    inputManager.addListener(actionListener, "Delete");
	    
	    inputManager.addListener(settingListener, "SwitchBlocUp");
	    inputManager.addListener(settingListener, "SwitchBlocDown");
	    inputManager.addListener(settingListener, "CreateForm");
	    inputManager.addListener(settingListener, "CreateFormFull");
	    //inputManager.addListener(playerControl, "rotateLeft");
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
}


