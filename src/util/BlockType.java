package util;

public final class BlockType {
	
	private final String name;
	private final String texture;
	
	
	public BlockType(String name, String texture)
	{
		this.name = name;
		this.texture = texture;
	}
	
	public String getTexture()
	{
		return this.texture;
	}
	
	public String toString()
	{
		return this.name;
	}
}
