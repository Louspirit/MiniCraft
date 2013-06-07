package macro;

import java.util.LinkedList;
import java.util.List;

import com.jme3.math.Vector3f;

import World.Block;
import World.IMapControl;
import World.Location;

public class Macro {

	private String nom;
	private Vector3f pointInit;
	private List<ActionMacro> actions;
	
	public Macro(String nom, Vector3f init)
	{
		this.nom=nom;
		this.pointInit = init;
		actions = new LinkedList<ActionMacro>();
	}
	
	
	public void recordMacro(ActionTypeMacro type, Block bloc, Vector3f cible)
	{
		Location coord = new Location(cible.subtract(pointInit));
		this.actions.add(new ActionMacro(type, bloc, coord));
	}


	public String getNom() {
		return nom;
	}

	public void replay(IMapControl control)
	{
		
	}
}
