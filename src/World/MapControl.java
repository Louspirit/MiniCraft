package World;

import java.util.HashMap;

import util.BlockType;

import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

import fenetre.Minicraft;

public class MapControl implements IMapControl {
	
	private Minicraft minicraft;
	private HashMap<Vector3f, Block> cartoMap;
	public Node map;
	private BulletAppState appState;
	
	public void init(Minicraft minicraft, BulletAppState appState) {
		this.minicraft = minicraft;
		cartoMap = new HashMap<Vector3f, Block>();
		this.appState = appState;
	}

	
	public Node generateMap(int longueur, int largeur, int hauteur) {
	 	map = new Node();
	 	BlockFactory blockFactory = new BlockFactory(minicraft.getAssetManager());
    	for (int i=1 ; i < longueur ; i++ ) {
    		for (int j = 1 ; j < largeur ; j++) {
    			Vector3f coord = new Vector3f(i, 0, j);
    			Block block = blockFactory.createBlock(BlockType.Dirt, coord);
    			appState.getPhysicsSpace().add(block.getBlocScape());
    	        map.attachChild(block.getGeometry());
    	        cartoMap.put(coord, block);
    		}
    	}
    	return map;
	}

	public boolean existBloc(int x, int y, int z) {
		return (cartoMap.get(new Vector3f(x,y,z))!=null);
	}
	
	public boolean existBloc(Vector3f coord) {
		return (cartoMap.get(coord)!=null);
	}
	
	public void attachBloc(Block bloc) {
		Vector3f coord = bloc.getGeometry().getLocalTranslation();
        cartoMap.put(coord, bloc);
		map.attachChild(bloc.getGeometry());
	}

}
