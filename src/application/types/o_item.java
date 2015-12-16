package application.types;

public class o_item
{
	public String name;
	
	public int weight;
	public int size[] = new int[3];
	
	public float durability; //0 extremely fragile, 1 never breaks;
	
	public boolean breakable;
	
	
	public o_item()
	{

	}
	
	public o_item(String name, int weight, int[] size, float durability, boolean breakable)
	{
		this.name = name;
		this.weight = weight;
		this.size = size;
		this.durability = durability;
		this.breakable = breakable;
	}
	
	public float get_volume()
	{
		return size[0] * size[1] * size[2];
		
	}
	
	public float get_value()
	{
		return (float) (weight * get_volume() * 0.6);
	}
	
	public int[] get_size()
	{
		return size;
	}
	
	public boolean get_breakable()
	{
		return breakable;
	}

}