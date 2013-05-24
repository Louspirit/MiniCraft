package fenetre;

import World.IMapControl;
import World.MapControl;

import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;

public class Minicraft extends SimpleApplication {
	
	private IMapControl mapControl;

	@Override
	public void simpleInitApp() {
		
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
