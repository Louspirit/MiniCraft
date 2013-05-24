package World;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

import fenetre.Minicraft;

public class MapControl implements IMapControl {
	
	private Minicraft minicraft;
	private Boolean[][][] cartoMap;
	public Node map;

	
	public void init(Minicraft minicraft) {
		this.minicraft = minicraft;
		cartoMap = new Boolean[100][100][100];
	}

	
	public Node generateMap(int longueur, int largeur, int hauteur) {
	 	map = new Node();
    	for (int i=1 ; i < longueur ; i++ ) {
    		for (int j = 1 ; j < largeur ; j++) {
    	        Box b = new Box(Vector3f.ZERO, 0.5f, 0.5f, 0.5f); // create cube shape at the origin
    	        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
    	        geom.setLocalTranslation(i, 0, j);
    	        Material mat = new Material(minicraft.getAssetManager(),
    	          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
    	        mat.setColor("Color", ColorRGBA.Green);   // set color of material to blue
    	        geom.setMaterial(mat);                   // set the cube's material
    	        map.attachChild(geom);
    	        cartoMap[i][0][j] = true;
    		}
    	}
    	return map;
	}

	public boolean isBloc(int x, int y, int z) {
		return cartoMap[x][y][z];
	}
	
	public void createBloc(Spatial bloc) {
		Vector3f coord = bloc.getLocalTranslation();
		cartoMap[(int)coord.getX()][(int)coord.getY()][(int)coord.getZ()] = true;
		map.attachChild(bloc);
	}

}
