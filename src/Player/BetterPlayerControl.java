/**
 * 
 */
package Player;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.PhysicsControl;

/**
 * @author roulleau
 *
 */
public class BetterPlayerControl implements IPlayerControl {

	BetterCharacterControl player;
	
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Player.IPlayerControl#getPlayer()
	 */
	@Override
	public PhysicsControl getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

}
