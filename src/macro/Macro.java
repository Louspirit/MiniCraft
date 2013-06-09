package macro;

import java.util.LinkedList;
import java.util.List;

import util.PCalcul;

import com.jme3.collision.CollisionResult;
import com.jme3.math.Vector3f;

import World.IMapControl;
import World.block.Block;
import World.block.BlockFactory;

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
		this.actions.add(new ActionMacro(type, bloc, cible.subtract(pointInit)));
	}


	public String getNom() {
		return nom;
	}

	public void replay(IMapControl map)
	{
		CollisionResult target = PCalcul.targetBloc();
		if(target != null)
		{
			Vector3f center =  target.getGeometry().getWorldBound().getCenter(), 
					coord =PCalcul.calculDirection(center, target.getContactPoint()).add(center);
			
		for(ActionMacro action : actions)
		{
			System.out.print("action :"+action.getType()+ ",coord rel : "+action.getCoord());
			if(action.getType() == ActionTypeMacro.addBlock)
			{
				System.out.println(", add : "+action.getCoord().add(coord).add(center));
				map.attachBloc(BlockFactory.createCopyBlock(action.getBloc(), action.getCoord().add(coord)));
			}
			if(action.getType() == ActionTypeMacro.removeBlock)
			{
				System.out.println("Del"+ action.getCoord().add(coord));
				map.detachBlock(map.getBlock(action.getCoord().add(coord)));
			}
		}
		}
		
	}
}
