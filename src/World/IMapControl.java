package World;

import com.jme3.scene.Node;

import fenetre.Minicraft;

/**
 * 
 * @author Benjamin
 *
 * Interface de gestion du monde
 *
 */
public interface IMapControl {
	
	public void init(Minicraft minicraft);
	public Node generateMap(int longueur, int largeur, int hauteur);

}
