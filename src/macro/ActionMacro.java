package macro;

import World.Block;
import World.Location;

public class ActionMacro {

	private ActionTypeMacro type;
	private Block bloc;
	private Location coord;
	
	
	public ActionMacro(ActionTypeMacro type, Block bloc, Location loc)
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


	public Location getCoord() {
		return coord;
	}
	
}
