package World;

import util.Constant;

import Player.PlayerSettingChoice;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

import fenetre.Minicraft;

public class BlockControl implements IBlockControl {
	
	private IMapControl mapControl;
	private Minicraft minicraft;
	public AssetManager assetManager;
	public PlayerSettingChoice setting;
	
	public BlockControl(IMapControl mapControl, Minicraft minicraft) {
		this.mapControl = mapControl;
		this.minicraft = minicraft;
		assetManager = this.minicraft.getAssetManager();
		setting = PlayerSettingChoice.getInstance();
	}

	@Override
	public void put(Block block, Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public Block newBlocNextTo(Block bloc, Vector3f direction, boolean attachBloc) {
	     Vector3f coord =  bloc.getCoord();
         Vector3f coordNewBloc = new Vector3f(
        		 coord.getX()+direction.x,coord.getY()+direction.y,coord.getZ()+direction.z);
                 
         int x1 = Math.round(coordNewBloc.getX());
         int y1 = Math.round(coordNewBloc.getY());
         int z1 = Math.round(coordNewBloc.getZ());
         
         Camera cam = minicraft.getCamera();
         int x2 = Math.round(cam.getLocation().getX());
         int y2 = Math.round(cam.getLocation().getY());
         int z2 = Math.round(cam.getLocation().getZ());
         
         if (attachBloc && !(x1 == x2 && (y1 == (y2-1) || y1 == y2) && z1 == z2)) {
             mapControl.attachBloc(new Block(assetManager,assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), coordNewBloc));     
         } else {
        	 return new Block(assetManager,assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), coordNewBloc);
         }
         return null;
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
		
		Block bloc = null;
		int diff = 0;
		
		switch (direction) {
		
			case "X" :
				int x1 = (int)start.x;
				int x2 = (int)end.x;
				diff = Math.abs(x1-x2);
				if (x1 < x2){
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x+i,start.y,start.z));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x-i,start.y,start.z));
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
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x,start.y+i,start.z));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x,start.y-i,start.z));
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
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x,start.y,start.z+i));
						mapControl.attachBloc(bloc);
					}
				} else {
					for (int i=0;i<=diff;i++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(start.x,start.y,start.z-i));
						mapControl.attachBloc(bloc);
					}
				}
				break;
				
			default :
				break;
		}
		
		return true;
	}
	
	/**
	 * Check if the two vectors are on a line (same x or y or z)
	 * @param start
	 * @param end
	 * @return
	 */
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
	
	public void createRectangle(Vector3f cornerBottomLeft, Vector3f cornerTopRight, boolean full) {
		int x1 = (int)cornerBottomLeft.x;
		int y1 = (int)cornerBottomLeft.y;
		int z1 = (int)cornerBottomLeft.z;
		int x2 = (int)cornerTopRight.x;
		int y2 = (int)cornerTopRight.y;
		int z2 = (int)cornerTopRight.z;
		
		int diffX = Math.abs(x1-x2);
		int diffY = Math.abs(y1-y2);
		int diffZ = Math.abs(z1-z2);
		
		Block bloc = null;
		
		if (x1>=x2) {
			int tmp = x2;
			x2 = x1;
			x1 = tmp;
		}
		
		if (y1>=y2) {
			int tmp = y2;
			y2 = y1;
			y1 = tmp;
		}
		
		if (z1>=z2) {
			int tmp = z2;
			z2 = z1;
			z1 = tmp;
		}
		
		if (!full) {

		for (int i=0 ; i <= diffX ; i++) {
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1+i,y1,z1));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1+i,y1,z2));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2-i,y2,z1));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2-i,y2,z2));
			mapControl.attachBloc(bloc);
		}
		

		
		for (int i=0 ; i <= diffY ; i++) {
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1,y1+i,z1));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1,y1+i,z2));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2,y2-i,z1));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2,y2-i,z2));
			mapControl.attachBloc(bloc);
		}
	
		
		for (int i=0 ; i <= diffZ ; i++) {
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1,y1,z1+i));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1,y2,z1+i));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2,y1,z2-i));
			mapControl.attachBloc(bloc);
			bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x2,y2,z2-i));
			mapControl.attachBloc(bloc);
		}
		
		} else {
			
			for (int i=0 ; i <= diffX ; i++) {
				for (int j=0 ; j <= diffY ; j++) {
					for (int k=0 ; k <= diffZ ; k++) {
						bloc = new Block(assetManager, assetManager.loadTexture(Constant.TEXTURES_PATH + setting.getTypeBloc()), new Vector3f(x1+i,y1+j,z1+k));
						mapControl.attachBloc(bloc);
					}
				}
			}
		}
		
	}

}
