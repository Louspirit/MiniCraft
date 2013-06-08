package World;

import util.BlockType;
import util.Constant;
import com.jme3.math.Vector3f;

import fenetre.Minicraft;

public class BlockFactory {
		
	public static Block createBlock(BlockType type, Vector3f coord) {
		String blockTexture = "";
		
		switch (type) {
			case Concrete:
				blockTexture = "beton.jpg";
				break;
			case Dirt:
				blockTexture = "terre.jpg";
				break;
			case Grass:
				blockTexture = "grass.jpg";
				break;
		}
		
		return new Block(null,Minicraft.getInstance().getAssetManager().loadTexture(Constant.TEXTURES_PATH + blockTexture), coord);
	}
	
	public static Block createCopyBlock(Block origin, Vector3f coord)
	{
		return new Block(origin, coord);
	}
}
