/**
 * 
 */
package World;


/**
 * @author roulleau
 *
 */
public class BlockMap implements IBlockMap{

	private Block[][][] carte;
	
	public BlockMap(int taille)
	{
		carte = new Block[taille][taille][taille];
	}

	@Override
	public Block get(int x, int y, int z) {
		// TODO Auto-generated method stub
		return carte[x][y][z];
	}

	@Override
	public void add(Block block) {
		Location loc = block.getLocation();
		
		carte[loc.getX()][loc.getY()][loc.getZ()] = block;
	}
	
	public void remove(int x, int y, int z)
	{
		carte[x][y][z] = null;
	}
	
	
}
