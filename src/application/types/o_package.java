package application.types;

public class o_package
{
	o_item item;
	
	public String name;
	
	public o_smartpost __from;
	public o_smartpost __to;

	public boolean breakable;
	
	public int __class;
	
	public int[] max_size = {10, 10, 10};
	public int max_weight;
	
	public float safety_rating;
	public float max_speed;
	
	public float max_distance;
	
	public void set_values(int c)
	{	
		for (int i = 0; i < 3; i++)
			max_size[i] = max_size[i] * c;
		
		this.max_weight = (1 * 2 * c);
		this.safety_rating = (float) (0.33 * c);
		this.max_speed = (float) (80.0 - (20 *  c));
		this.max_distance = 150 + (400 * c);
	}

	public o_package(o_item __item, o_smartpost f, o_smartpost t, int c)
	{
		this.name = "Package: " + __item.name + ", Class: " + c;
		this.item = __item;
		this.__from = f;
		this.__to = t;
		this.__class = c;
		this.breakable = __item.breakable;
		
		for (int i = 0; i < 3; i++)
			max_size[i] = max_size[i] * c;
		
		this.max_weight = (1 * 2 * c);
		this.safety_rating = (float) (0.33 * c);
		this.max_speed = (float) (80.0 - (20 *  c));
		this.max_distance = 150 + (400 * (c - 1));
		
		set_values(__class);
	}
}