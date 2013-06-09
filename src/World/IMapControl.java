package World;

import World.block.Block;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * 
 * @author Benjamin
 *
 * Interface de gestion du monde
 *
 */
public interface IMapControl {
	
	public Node generateMap(int longueur, int largeur, int hauteur);
	public boolean existBloc(int x, int y, int z);
	public boolean existBloc(Vector3f coord);
	public void attachBloc(Block bloc);
	public Block getBlock(Vector3f coord);
	public Block getBlock(int x, int y, int z);
	public boolean detachBlock(Block bloc);
	

}
