/**
 * 
 */
package World;

import World.block.Block;

/**
 * @author roulleau
 *
 */
public interface IBlockMap {

	Block get(int x, int y, int z);
	void add(Block block);
	void remove(int x, int y, int z);
}
