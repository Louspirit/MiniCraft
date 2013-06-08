package World;

import com.jme3.math.Vector3f;

/**
 * 
 * @author Benjamin
 *
 * Interface de gestion des blocs
 * 
 */
public interface IBlockControl {
	
	// public void removeBloc();
	
	public void put(Block block, Location location);
	public Block newBlocNextTo(Block bloc, Vector3f direction, boolean attachBlock);
	public boolean deleteBloc(Block bloc);
	public boolean createLine(Vector3f start, Vector3f end);
	public void createRectangle(Vector3f cornerBottomLeft, Vector3f cornerTopRight, boolean full);
	
}
