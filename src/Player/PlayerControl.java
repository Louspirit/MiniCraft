/**
 * 
 */
package Player;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 * @author roulleau
 * Implementation de IPlayerControl avec CharacterControl
 */
public class PlayerControl implements IPlayerControl {

	private CharacterControl player;
    private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false;
    private Camera cam;
    
	public PlayerControl(Camera camera)
	{
		this.cam = camera;
		// Utilisation de BetterCharacterControl pour la forme du joueur 
		// et pour gérer sa physique
		CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.45f, 1.95f, 1);
	    player = new CharacterControl(capsuleShape, 0.015f);
	    player.setJumpSpeed(20);
	    player.setFallSpeed(10);
	    player.setGravity(70);
	    player.setPhysicsLocation(new Vector3f(4, 5, 4));

	}
	
	/* (non-Javadoc)
	 * @see com.jme3.input.controls.ActionListener#onAction(java.lang.String, boolean, float)
	 */
	@Override
	public void onAction(String binding, boolean value, float tpf) {
	   if (binding.equals("Left")) {
		      left = value;
		    } else if (binding.equals("Right")) {
		      right = value;
		    } else if (binding.equals("Up")) {
		      up = value;
		    } else if (binding.equals("Down")) {
		      down = value;
		    } else if (binding.equals("Jump")) {
		      player.jump();
		    }
	}


	@Override
	public void walk() {
		// TODO Auto-generated method stub
		Vector3f camDir = cam.getDirection().clone().multLocal(0.15f);
	    Vector3f camLeft = cam.getLeft().clone().multLocal(0.1f);
	    walkDirection.set(0, 0, 0);
	    if (left)  { walkDirection.addLocal(camLeft); }
	    if (right) { walkDirection.addLocal(camLeft.negate()); }
	    if (up)    { walkDirection.addLocal(camDir); }
	    if (down)  { walkDirection.addLocal(camDir.negate()); }
	    player.setWalkDirection(walkDirection);
	    cam.setLocation(player.getPhysicsLocation());
	}


	@Override
	public CharacterControl getPlayer() {
		return player;
	}
}
