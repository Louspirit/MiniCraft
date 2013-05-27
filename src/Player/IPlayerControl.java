package Player;

import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;

/**
 * 
 * @author Benjamin
 * @author roulleau
 * 
 * Interface du joueur
 *
 */
public interface IPlayerControl extends ActionListener {
	
	// attribut vie
	// macros
	
	/**
	 * Fait avancer le personnage
	 */
	public void walk();
	
	/**
	 * Renvois la position du personnage
	 * @return le vecteur de la position
	 */
	public Vector3f getPhysicLocation();
	
	/**
	 * Renvois le joueur
	 * @return le joueur
	 */
	public CharacterControl getPlayer();
}
