package fenetre;

import listener.BlocListener;
import util.Constant;
import Player.IPlayerControl;
import Player.PlayerControl;

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
	
	@Override
	public void simpleInitApp() {
		
	    initCrossHairs(); // a "+" in the middle of the screen to help aiming
		
		blockControl = new BlockControl(mapControl, this);
		
	    actionListener = new BlocListener(cam, mapControl, blockControl, map);
	    initKeys();       // load custom key mappings
	    			    
		initCam();	

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
		
	    playerControl = new PlayerControl(cam);
	    setUpKeys();

	    bulletAppState.getPhysicsSpace().add(playerControl.getPlayer());
	}
	
	private void initCam() {
		cam.setLocation(new Vector3f(8, 2, 8));
		flyCam.setMoveSpeed(40);
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
	    inputManager.addListener(playerControl, "Left");
	    inputManager.addListener(playerControl, "Right");
	    inputManager.addListener(playerControl, "Up");
	    inputManager.addListener(playerControl, "Down");
	    inputManager.addListener(playerControl, "Jump");
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
  
  /** Declaring the "Shoot" action and mapping to its triggers. */
  private void initKeys() {
    inputManager.addMapping("Add",
      new KeyTrigger(KeyInput.KEY_SPACE), // trigger 1: spacebar
      new MouseButtonTrigger(MouseInput.BUTTON_LEFT)); // trigger 2: left-button click
    inputManager.addListener(actionListener, "Add");
    inputManager.addMapping("Delete",
    	      new KeyTrigger(KeyInput.KEY_DELETE), // trigger 1: spacebar
    	      new MouseButtonTrigger(MouseInput.BUTTON_RIGHT)); // trigger 2: left-button click
    	    inputManager.addListener(actionListener, "Delete");
  }

}


