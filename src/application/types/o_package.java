package application.types;

public class o_package
{
	private o_item item;
	
	private String name;
	
	private o_smartpost __from;
	private o_smartpost __to;

	private boolean breakable;
	
	private int __class;
	
	private int[] max_size = {10, 10, 10};
	private int max_weight;
	
	private float safety_rating;
	private float max_speed;
	
	private float max_distance;
	
	public boolean fits(o_item __item, o_smartpost f, o_smartpost t, int __c)
	{
		
		return false;
	}
	
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
		if (!fits(__item, f, t, c))
			return;
		
		this.name = "Package: " + __item.name + ", Class: " + c;
		this.item = __item;
		this.__from = f;
		this.__to = t;
		this.__class = c;
		this.breakable = __item.breakable;
		
		for (int i = 0; i < 3; i++)
			max_size[i] = max_size[i] * c;
		
		set_values(__class);
	}

	public o_item getItem() {
		return item;
	}

	public String getName() {
		return name;
	}

	public o_smartpost get_from() {
		return __from;
	}

	public o_smartpost get_to() {
		return __to;
	}

	public boolean isBreakable() {
		return breakable;
	}

	public int get_class() {
		return __class;
	}

	public int[] getMax_size() {
		return max_size;
	}

	public int getMax_weight() {
		return max_weight;
	}

	public float getSafety_rating() {
		return safety_rating;
	}

	public float getMax_speed() {
		return max_speed;
	}

	public float getMax_distance() {
		return max_distance;
	}

	public void setItem(o_item item) {
		this.item = item;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void set_from(o_smartpost __from) {
		this.__from = __from;
	}

	public void set_to(o_smartpost __to) {
		this.__to = __to;
	}

	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}

	public void set_class(int __class) {
		this.__class = __class;
	}

	public void setMax_size(int[] max_size) {
		this.max_size = max_size;
	}

	public void setMax_weight(int max_weight) {
		this.max_weight = max_weight;
	}

	public void setSafety_rating(float safety_rating) {
		this.safety_rating = safety_rating;
	}

	public void setMax_speed(float max_speed) {
		this.max_speed = max_speed;
	}

	public void setMax_distance(float max_distance) {
		this.max_distance = max_distance;
	}
}