package fenetre;

import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.BetterCharacterControl;

public class Minicraft extends SimpleApplication {
	
	private IMapControl mapControl;
	
	// Gestion de la physique
	private BulletAppState bulletAppState;
	
	@Override
	public void simpleInitApp() {
	    
		mapControl = new MapControl();
		mapControl.init(this);
		flyCam.setMoveSpeed(80);
		Node carte = mapControl.generateMap(16, 16, 1);
		rootNode.attachChild(carte);		
	    
		/** Initialise la physique (collisions) */
	    bulletAppState = new BulletAppState();
	    stateManager.attach(bulletAppState);
	    /** En cas de débugage **/
	    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
	    
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Minicraft minicraft = new Minicraft();
		minicraft.start();
	}


	private BetterCharacterControl createPlayer()
	{
		// Utilisation de BetterCharacterControl pour la forme du joueur 
		// et pour gérer sa physique
		BetterCharacterControl player = new BetterCharacterControl(1.5f, 6f, 30);
		
		return player;
	}
}


