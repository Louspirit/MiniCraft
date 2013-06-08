package util;

public enum BlockType {
	
	Concrete("Pierre",Constant.CONCRETE), 
	Dirt("Terre", Constant.DIRT), 
	Grass("Herbe", Constant.GRASS),
	Wood("Bois", Constant.WOOD),
	Water("Eau", Constant.WATER),
	Puppy("Chiot", Constant.PUPPY);
	
	
	private final String name;
	private final String texture;
	
	
	private BlockType(String name, String texture)
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
