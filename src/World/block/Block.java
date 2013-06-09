package World.block;

import World.Location;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

import fenetre.Minicraft;

public class Block {
	
	private Geometry geometry;
	private Vector3f coord;
	private RigidBodyControl blocscape;

	
	protected Block(Texture texture, Vector3f coord) {
		Material material = new Material(Minicraft.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		material.setTexture("ColorMap", texture);
		blockBuilder(material, coord);
	}
	
	protected Block(Block origin, Vector3f coord)
	{
		blockBuilder(origin.geometry.getMaterial().clone(), coord);
	}
	
	@SuppressWarnings("deprecation")
	private void blockBuilder(Material material, Vector3f coord)
	{
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
	
	/**
	 * 
	 * @return renvois les coordonées entière du bloc
	 */
	public Location getLocation()
	{
		return new Location((int) coord.x, (int)coord.y, (int)coord.z);
	}
}
