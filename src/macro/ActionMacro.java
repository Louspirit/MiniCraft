package macro;

import com.jme3.math.Vector3f;

import World.block.Block;

public class ActionMacro {

	private ActionTypeMacro type;
	private Block bloc;
	private Vector3f coord;
	
	
	public ActionMacro(ActionTypeMacro type, Block bloc, Vector3f loc)
	{
		this.type = type;
		this.bloc = bloc;
		this.coord = loc;
	}


	public ActionTypeMacro getType() {
		return type;
	}


	public Block getBloc() {
		return bloc;
	}


	public Vector3f getCoord() {
		return coord;
	}
	
}
