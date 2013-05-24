package fenetre;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;

public class Minicraft extends SimpleApplication {
	// Gestion de la physique
	private BulletAppState bulletAppState;
	
	@Override
	public void simpleInitApp() {
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

}
