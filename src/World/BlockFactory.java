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
		Block block = null;
		
		switch (type) {
			case Concrete:
				block = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + "beton.jpg"), coord);
				break;
		}
		
		return block;
	}
	
}
