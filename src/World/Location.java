package World;

import com.jme3.math.Vector3f;


public class Location {
	
	private int x;
	private int y;
	private int z;

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location(Vector3f vector)
	{
		this.x = (int)vector.x;
		this.y = (int)vector.y;
		this.z = (int)vector.z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
}
