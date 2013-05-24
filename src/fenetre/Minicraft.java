package fenetre;

import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.bullet.BulletAppState;

public class Minicraft extends SimpleApplication {
	
	private IMapControl mapControl;
	// Gestion de la physique
	private BulletAppState bulletAppState;
	
	@Override
	public void simpleInitApp() {
		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
	    
		mapControl = new MapControl();
		mapControl.init(this);
		flyCam.setMoveSpeed(80);
		Node carte = mapControl.generateMap(16, 16, 1);
		rootNode.attachChild(carte);		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Minicraft minicraft = new Minicraft();
		minicraft.start();
	}

}
