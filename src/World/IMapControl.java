package World;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

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
	public boolean isBloc(int x, int y, int z);
	public void createBloc(Spatial bloc);

}
