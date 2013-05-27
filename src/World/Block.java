package World;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Block {
	
	private Geometry geometry;
	private Vector3f coord;
	private RigidBodyControl blocscape;

	public Block(AssetManager assetManager, Texture texture, Vector3f coord) {
		Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		material.setTexture("ColorMap", texture);
		this.coord = coord;
		geometry = new Geometry("Box", new Box(coord, 0.5f, 0.5f, 0.5f));
		geometry.setMaterial(material);
		
		// We set up collision detection for the scene by creating a
	    // compound collision shape and a static RigidBodyControl with mass zero.
	    CollisionShape sceneShape =
	            CollisionShapeFactory.createMeshShape(geometry);
	    blocscape = new RigidBodyControl(sceneShape, 0);
	    geometry.addControl(blocscape);
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public RigidBodyControl getBlocScape()
	{
		return this.blocscape;
	}
	
	public Vector3f getCoord() {
		return coord;
	}
}
