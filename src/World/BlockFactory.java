package World;

import util.BlockType;
import util.Constant;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;

public class BlockFactory {
	
	private AssetManager assetManager;
	
	public BlockFactory(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	public Block createBlock(BlockType type, Vector3f coord) {
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
		
		return new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + blockTexture), coord);
	}
	
}
