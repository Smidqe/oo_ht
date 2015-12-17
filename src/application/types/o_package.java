package application.types;

public class o_package
{
	o_item item;
	
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
		this.max_distance = 150 + (25 * c);
	}

	o_package(o_item __item, o_smartpost f, o_smartpost t, int c)
	{
		this.item = __item;
		this.__from = f;
		this.__to = t;
		this.__class = c;
		
		
		/*
		if distance(f, t) > class_max_dist(c)
			return null;
		 
		
		if !item_fits(c) 
			return null;
		 
		 
		if  
		 */
		
		set_values(__class);
	}
}