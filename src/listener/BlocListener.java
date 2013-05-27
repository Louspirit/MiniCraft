package listener;

import World.*;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class BlocListener implements ActionListener {
	
	private Camera cam;
	private IMapControl mapControl;
	private IBlockControl blockControl;
	private Node map;

	public BlocListener(Camera cam, IMapControl mapControl, IBlockControl blockControl, Node map) {
		this.cam = cam;
		this.mapControl = mapControl;
		this.blockControl = blockControl;
		this.map = map;
	}

	public void onAction(String name, boolean keyPressed, float tpf) {
		 if ((name.equals("Add") || name.equals("Delete")) && !keyPressed) {
		        // 1. Reset results list.
		        CollisionResults results = new CollisionResults();
		        // 2. Aim the ray from cam loc to cam direction.
		        Ray ray = new Ray(cam.getLocation(), cam.getDirection());
		        // 3. Collect intersections between Ray and Shootables in results list.
		        map.collideWith(ray, results);
		        // 4. Print the results
		        System.out.println("----- Collisions? " + results.size() + "-----");
		        for (int i = 0; i < results.size(); i++) {
		          // For each hit, we know distance, impact point, name of geometry.
		          float dist = results.getCollision(i).getDistance();
		          Vector3f pt = results.getCollision(i).getContactPoint();
		          String hit = results.getCollision(i).getGeometry().getName();
		          System.out.println("* Collision #" + i);
		          System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
		        }
		        // 5. Use the results (we mark the hit object)
		        if (results.size() > 0) {
		          // The closest collision point is what was truly hit:
		          CollisionResult closest = results.getClosestCollision();
		          // Let's interact - we mark the hit with a red dot.
		          Vector3f coord =  closest.getContactPoint();
		          int x =  (int)Math.floor(coord.getX());
		          int y = (int)Math.floor(coord.getY());
		          int z = (int)Math.floor(coord.getZ());
		          if (mapControl.existBloc(x,y,z)) {
		        	   Block block = mapControl.getBlock(x,y,z);
		        	   if (name.equals("Add")) {
		                   blockControl.newBlocNextTo(block, null);
		        	   } else if (name.equals("Delete")) {
		            	   blockControl.deleteBloc(block);
		        	   }
		          }
		        }
		 }
	}

}
