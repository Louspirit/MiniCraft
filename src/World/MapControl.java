package World;


import util.BlockType;
import com.jme3.bullet.BulletAppState;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import fenetre.Minicraft;

public class MapControl implements IMapControl {
	
	private Minicraft minicraft;
	//private HashMap<Integer[][][], Block> cartoMap;
	private IBlockMap cartoMap;
	public Node map;
	public AssetManager assetManager;
	private BulletAppState appState;

	
	public void init(Minicraft minicraft, BulletAppState appState) {
		this.minicraft = minicraft;
		//cartoMap = new HashMap<Integer[][][], Block>();
		cartoMap = new BlockMap(100);
		assetManager = this.minicraft.getAssetManager();
		this.appState = appState;
	}

	
	public Node generateMap(int longueur, int largeur, int hauteur) {
	 	map = new Node();
	 	BlockFactory blockFactory = new BlockFactory(assetManager);
    	for (int i=1 ; i < longueur ; i++ ) {
    		for (int j = 1 ; j < largeur ; j++) {
    			Vector3f coord = new Vector3f(i, 0, j);
    			Block block = blockFactory.createBlock(BlockType.Dirt, coord);

    			this.attachBloc(block);
    		}
    	}
    	return map;
	}

	public boolean existBloc(int x, int y, int z) {
		return cartoMap.get(x, y, z) != null;
	}
	
	public boolean existBloc(Vector3f coord) {
		return existBloc((int)coord.x,(int)coord.y,(int)coord.z);
	}
	
	public void attachBloc(Block bloc) {
		
		try{
			cartoMap.add(bloc);
			map.attachChild(bloc.getGeometry());
			appState.getPhysicsSpace().add(bloc.getBlocScape());
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
	}
	
	public Block getBlock(int x, int y, int z) {
		return cartoMap.get(x, y, z);
	}
	
	public Block getBlock(Vector3f coord) {
		return getBlock((int)coord.x,(int)coord.y,(int)coord.z);
	}
	
	public boolean detachBlock(Block bloc) {
		Location coord = bloc.getLocation();
		
		if (existBloc(coord.getX(), coord.getY(), coord.getZ())) {
			map.detachChild(bloc.getGeometry());
			appState.getPhysicsSpace().remove(bloc.getBlocScape());
			cartoMap.remove(coord.getX(), coord.getY(), coord.getZ());
			return true;
		} 
		else 
		{
			return false;
		}
	}

}
