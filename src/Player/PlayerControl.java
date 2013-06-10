/**
 * 
 */
package Player;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 * @author roulleau
 * Implementation de IPlayerControl avec CharacterControl
 */
@SuppressWarnings("deprecation")
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
		CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(0.45f,1.10f, 1);
	    player = new CharacterControl(capsuleShape, 0.015f);
	    player.setJumpSpeed(18);
	    player.setFallSpeed(13f);
	    player.setGravity(90);
	    player.setPhysicsLocation(new Vector3f(4, 6, 4));

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
	public void onAnalog(String binding, float value, float tpf) {

	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		if (player.getPhysicsLocation().y <= -10) {
			player.setPhysicsLocation(new Vector3f(4, 20, 4));
		} else {
			Vector3f camDir = cam.getDirection().clone().multLocal(0.15f);
		    Vector3f camLeft = cam.getLeft().clone().multLocal(0.1f);
		    walkDirection.set(0, 0, 0);
		    if (left)  { walkDirection.addLocal(camLeft); }
		    if (right) { walkDirection.addLocal(camLeft.negate()); }
		    if (up)    { walkDirection.addLocal(camDir); }
		    if (down)  { walkDirection.addLocal(camDir.negate()); }
		    player.setWalkDirection(walkDirection);
		    cam.setLocation(player.getPhysicsLocation().add(new Vector3f(0,0.5f,0)));	
		}
	}


	@Override
	public PhysicsControl getPlayer() {
		
		return player;
	}
}
