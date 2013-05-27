package World;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Block {
	
	private Geometry geometry;
	private Vector3f coord;

	public Block(AssetManager assetManager, Texture texture, Vector3f coord) {
		Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		material.setTexture("ColorMap", texture);
		geometry = new Geometry("Box", new Box(coord, 0.5f, 0.5f, 0.5f));
		geometry.setMaterial(material);
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
}
