package World;

import java.util.HashMap;

import jme3tools.navigation.StringUtil;

import util.Constant;

import Player.PlayerSettingChoice;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.sun.xml.internal.ws.util.StringUtils;

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
	
	public boolean createLine(Vector3f start, Vector3f end) {
		String direction = isLine(start,end);
		if (direction.equals("ERROR")) {
			// throw une erreur ?
			return false;
		}
		
		Node line = new Node();
		Block bloc = null;
		int diff = 0;
		
		switch (direction) {
		
			case "X" :
				int x1 = (int)start.x;
				int x2 = (int)end.x;
				diff = Math.abs(x1-x2);
				if (x1 < x2){
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x+i,start.y,start.z));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x-i,start.y,start.z));
						mapControl.attachBloc(bloc);
					}
				}
				break;
				
			case "Y" :
				int y1 = (int)start.x;
				int y2 = (int)end.x;
				diff = Math.abs(y1-y2);
				if (y1 < y2){
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x,start.y+i,start.z));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x,start.y-i,start.z));
						mapControl.attachBloc(bloc);
					}
				}
				break;
				
			case "Z" :
				int z1 = (int)start.x;
				int z2 = (int)end.x;
				diff = Math.abs(z1-z2);
				if (z1 < z2){
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x,start.y,start.z+i));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + PlayerSettingChoice.getTypeBloc()), new Vector3f(start.x,start.y,start.z-i));
						mapControl.attachBloc(bloc);
					}
				}
				break;
				
			default :
				break;
		}
		
		return true;
	}
	
	public String isLine(Vector3f start,Vector3f end) {
		int x1 = (int)start.x;
		int y1 = (int)start.y;
		int z1 = (int)start.z;
		int x2 = (int)end.x;
		int y2 = (int)end.y;
		int z2 = (int)end.z;
		
		if (x1==x2 && y1==y2) {
			return Constant.Z;
		} else if (x1==x2 && z1==z2) {
			return Constant.Y;
		} else if (y1==y2 && z1==z2) {
			return Constant.X;
		} else {
			return Constant.ERROR;
		}
	}

}
