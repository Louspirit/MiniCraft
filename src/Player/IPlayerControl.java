package Player;

import com.jme3.bullet.control.PhysicsControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;

/**
 * 
 * @author Benjamin
 * @author roulleau
 * 
 * Interface du joueur
 *
 */
public interface IPlayerControl extends ActionListener, AnalogListener {
	
	
	/**
	 * Fait avancer le personnage
	 */
	public void walk();
	
	
	/**
	 * Renvois le joueur
	 * @return le joueur
	 */
	public PhysicsControl getPlayer();
}
