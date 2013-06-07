package util;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;

import fenetre.Minicraft;

public class PCalcul {
	
	public static CollisionResult targetBloc()
	{	
		// 1. Reset results list.
		CollisionResults results = new CollisionResults();
		// 2. Aim the ray from cam loc to cam direction.
		Ray ray = new Ray(Minicraft.getInstance().getCamera().getLocation(), 
				Minicraft.getInstance().getCamera().getDirection());
		// 3. Collect intersections between Ray and Bloc in results list.
		Minicraft.getInstance().getMap().collideWith(ray, results);

		/**
		 * S'il y a un resultat
		 */
		if (results.size() > 0)
		{
			// The closest collision point is what was truly hit:
			return results.getClosestCollision();
		}
		return null;
	}
	/**
	 * Determine la direction où placer le bloc en comparant le point d'impact
	 * par rapport au centre du bloc visé
	 * @param center centre du bloc visé
	 * @param contactPt point d'impact
	 * @return renvois la translation à éffectuer pour placer le bloc
	 */
	public static Vector3f calculDirection(Vector3f center, Vector3f contactPt)
	{
		Vector3f direction = new Vector3f(0,0,0), coord = center.subtract(contactPt);

		direction.x = calculXYZ(coord.x);
		direction.y = calculXYZ(coord.y);
		direction.z = calculXYZ(coord.z);
		return direction;
	}


	public static int calculXYZ(float coord)
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
	public static float arrondi(float n, int precision)
	{
		return (float)(Math.floor((n*Math.pow(10, precision)+0.5f))/(Math.pow(10, precision)));
	}
}
