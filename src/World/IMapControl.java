package World;

import com.jme3.scene.Node;

/**
 * 
 * @author Benjamin
 *
 * Interface de gestion du monde
 *
 */
public interface IMapControl {
	
	public void init();
	public Node generateMap(int longueur, int largeur, int hauteur);

}
