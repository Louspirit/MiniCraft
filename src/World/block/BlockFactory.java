package World.block;

import util.BlockType;
import util.Constant;
import com.jme3.math.Vector3f;

import fenetre.Minicraft;

public class BlockFactory {
		
	public static Block createBlock(BlockType type, Vector3f coord)
	{
		return new Block(Minicraft.getInstance().getAssetManager().loadTexture(Constant.TEXTURES_PATH + type.getTexture()), coord);
	}
	
	public static Block createCopyBlock(Block origin, Vector3f coord)
	{
		return new Block(origin, coord);
	}
}
