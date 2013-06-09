package World;


import macro.MacroStore;
import Player.PlayerSettingChoice;
import World.block.Block;
import World.block.BlockFactory;

import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class MapControl implements IMapControl {
	
	private IBlockMap cartoMap;
	public Node map;
	private BulletAppState appState;
	private MacroStore macro;

	
	public MapControl(BulletAppState appState, MacroStore macro) {
		cartoMap = new BlockMap(100);
		this.appState = appState;
		this.macro= macro;
	}

	
	public Node generateMap(int longueur, int largeur, int hauteur) {
	 	map = new Node();

    			for (int i=1 ; i <= longueur ; i++ ) {
    				for (int j = 1 ; j <= largeur ; j++) {
    					for (int k=1 ; k <= hauteur ; k++) {
    						Vector3f coord = new Vector3f(i, k, j);
    						Block block = BlockFactory.createBlock(
    								PlayerSettingChoice.getInstance().getDefautType(), coord);

        			this.attachBloc(block);
    			}
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
		Location coord = bloc.getLocation();
		try{
			if (existBloc(coord.getX(), coord.getY(), coord.getZ())) 
			{
				detachBlock(getBlock(coord.getX(), coord.getY(), coord.getZ()));
			}
			cartoMap.add(bloc);
			map.attachChild(bloc.getGeometry());
			appState.getPhysicsSpace().add(bloc.getBlocScape());
			macro.addBloc(bloc);
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
			macro.removeBoc(bloc);
			return true;
		} 
		else 
		{
			return false;
		}
	}

}
