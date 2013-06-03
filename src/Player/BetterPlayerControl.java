/**
 * 
 */
package Player;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl.ControlDirection;

/**
 * @author roulleau
 *
 */
public class BetterPlayerControl implements IPlayerControl, AnalogListener {

	private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false;
	private BetterCharacterControl player;
	private Node playerNode;
	private CameraNode camNode;
	
	public BetterPlayerControl(Node rootNode, Camera cam)
	{
		// Create a node for the character model
        playerNode = new Node("character node");

        // Add a character control to the node so we can add other things and
        // control the model rotation
        player = new BetterCharacterControl(0.3f, 2.5f, 8f);
        playerNode.addControl(player);

        // Add character node to the rootNode
        rootNode.attachChild(playerNode);

        camNode = new CameraNode("CamNode", cam);
        camNode.setControlDir(ControlDirection.SpatialToCamera);
        playerNode.attachChild(camNode);	
        
	}
	
	/* (non-Javadoc)
	 * @see com.jme3.input.controls.ActionListener#onAction(java.lang.String, boolean, float)
	 */
	@Override
	public void onAction(String arg0, boolean arg1, float arg2) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Player.IPlayerControl#walk()
	 */
	@Override
	public void walk() {
		 // Get current forward and left vectors of model by using its rotation
        // to rotate the unit vectors
        Vector3f modelForwardDir = playerNode.getWorldRotation().mult(Vector3f.UNIT_Z);
        Vector3f modelLeftDir = playerNode.getWorldRotation().mult(Vector3f.UNIT_X);

        // WalkDirection is global!
        // You *can* make your character fly with this.
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(modelLeftDir.mult(3));
        } else if (right) {
            walkDirection.addLocal(modelLeftDir.negate().multLocal(3));
        }
        if (up) {
            walkDirection.addLocal(modelForwardDir.mult(3));
        } else if (down) {
            walkDirection.addLocal(modelForwardDir.negate().multLocal(3));
        }
        player.setWalkDirection(walkDirection);


	}

	
	/* (non-Javadoc)
	 * @see Player.IPlayerControl#getPlayer()
	 */
	@Override
	public PhysicsControl getPlayer() {
		return player;
	}

	@Override
	public void onAnalog(String name, float value, float tpf) {
		// TODO Auto-generated method stub
		
	    if (name.equals("rotateRight")) {
	        
	      }
      if (name.equals("rotateLeft")) {
        
      }

	}

}
