package World;

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
	public void newBlocNextTo(Block bloc, String direction);
	public boolean deleteBloc(Block bloc);
	
}
