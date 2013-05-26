package World;

import com.jme3.asset.AssetManager;

public class BlockFactory {
	
	private static final String TEXTURES_PATH = "Textures/";
	private AssetManager assetManager;
	
	public BlockFactory(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	public Block createBlock(BlockType type) {
		Block block = null;
		
		switch (type) {
			case Dirt:
				block = new Block(assetManager, assetManager.loadTexture(TEXTURES_PATH + "beton.jpg"));
				break;
		}
		
		return block;
	}
	
}
