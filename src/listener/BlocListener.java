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
		        // 3. Collect intersections between Ray and Bloc in results list.
		        map.collideWith(ray, results);

		        /**
		         * S'il y a un resultat
		         */
		        if (results.size() > 0) {
		          // The closest collision point is what was truly hit:
		          CollisionResult closest = results.getClosestCollision(), bottomBloc;

		          Ray rayVertical = new Ray(cam.getLocation(),new Vector3f(0,-3,0));
		          map.collideWith(rayVertical, results);
		          if(results.size() > 0) bottomBloc = results.getClosestCollision();
		          else bottomBloc = new CollisionResult();
		          
		          Vector3f coord =  closest.getGeometry().getWorldBound().getCenter();

		          if (mapControl.existBloc(coord) && closest.getDistance() < 5) {
		        	   Block block = mapControl.getBlock(coord);
		        	   if (name.equals("Add")) {
		                   blockControl.newBlocNextTo(block, calculDirection(coord, closest.getContactPoint()));
		        	   } else if (name.equals("Delete")) {
		            	   blockControl.deleteBloc(block);
		        	   }
		          }
		        }
		 }
	}
	
	/**
	 * Determine la direction où placer le bloc en comparant le point d'impact
	 * par rapport au centre du bloc visé
	 * @param center centre du bloc visé
	 * @param contactPt point d'impact
	 * @return renvois la translation à éffectuer pour placer le bloc
	 */
	private Vector3f calculDirection(Vector3f center, Vector3f contactPt)
	{
		Vector3f direction = new Vector3f(0,0,0), coord = center.subtract(contactPt);
		
		 direction.x = calculXYZ(coord.x);
		 direction.y = calculXYZ(coord.y);
		 direction.z = calculXYZ(coord.z);
		return direction;
	}
	
	
	private int calculXYZ(float coord)
	{
		if(arrondi(coord,2) == 0.5) return -1;
		else if(arrondi(coord,2) == -0.5) return 1;
		return 0;
	}
	
	/**
	 * Calcul l'arrondi
	 * @param n le chiffre à arrondir 
	 * @param precision precision après la virgule
	 * @return le chiffre arrondi
	 */
	private float arrondi(float n, int precision)
	{
		return (float)(Math.floor((n*Math.pow(10, precision)+0.5f))/(Math.pow(10, precision)));
	}

}
