package World;

import java.util.HashMap;

import util.Constant;

import Player.PlayerSettingChoice;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import fenetre.Minicraft;

public class BlockControl implements IBlockControl {
	
	private IMapControl mapControl;
	private Minicraft minicraft;
	public AssetManager assetManager;
	
	public BlockControl(IMapControl mapControl, Minicraft minicraft) {
		this.mapControl = mapControl;
		this.minicraft = minicraft;
		assetManager = this.minicraft.getAssetManager();
	}

	@Override
	public void put(Block block, Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newBlocNextTo(Block bloc, Vector3f direction) {
	     Vector3f coord =  bloc.getCoord();
         Vector3f coordNewBloc = new Vector3f(
        		 coord.getX()+direction.x,coord.getY()+direction.y,coord.getZ()+direction.z);
         mapControl.attachBloc(new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), coordNewBloc));    
	}
	
	public boolean deleteBloc(Block bloc) {
		return mapControl.detachBlock(bloc);
	}

}
