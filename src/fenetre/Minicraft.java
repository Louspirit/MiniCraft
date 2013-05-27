package fenetre;

import Player.IPlayerControl;
import Player.PlayerControl;

import World.Block;
import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;

public class Minicraft extends SimpleApplication {
	
	private IMapControl mapControl;
	private IPlayerControl playerControl;
	// Gestion de la physique
	private BulletAppState bulletAppState;
	
	@Override
	public void simpleInitApp() {
	    
		initCam();	

		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
		
		mapControl = new MapControl();
		mapControl.init(this, bulletAppState);
		flyCam.setMoveSpeed(10);
		Node carte = mapControl.generateMap(16, 16, 1);
		rootNode.attachChild(carte);		
	    		
		

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

}


