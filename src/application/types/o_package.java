package application.types;

import application.files.log;

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
	
    @Override
	public String toString() {
		return "o_package [item=" + item + ", name=" + name + ", __from=" + __from + ", __to=" + __to + ", __class="
				+ __class + "]";
	}

    //calculates the bird's distance between two gps points. IT cannot into math.
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
    	final double R = 6372.2;
    	
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return R * c;
    }
	
	//checks if the item fills the regulations, we don't want oddities in our storage!
	public boolean fits(o_item __item, o_smartpost f, o_smartpost t, int __c)
	{
		//checks the distance if it's too far away.
		if ((distance(Double.parseDouble(f.getGps_lat()), Double.parseDouble(f.getGps_lng()), Double.parseDouble(t.getGps_lat()), Double.parseDouble(t.getGps_lng()))) > 150 + (400 * (__c - 1)))
		{
			log.getInstance().entry("Etäisyys on liian pitkä: Luokka:" + __c, false);
			return false;
		}
		
		//checks the item dimensions.
		if (((max_size[0] * __c) < __item.size[0]) || ((max_size[1] * __c) < __item.size[1]) || ((max_size[2] * __c) < __item.size[2]))
		{
			log.getInstance().entry("Objekti on liian suuri pakkaukseen: Luokka:" + __c, false);
			return false;	
		}	
		return true;
	}
	
	//sets the values for necessary variables, mainly increases as the class increases.
	public void set_values(int c)
	{	
		if (c == 2)
			for (int i = 0; i < 3; i++)
				max_size[i] = 5;
		else
			for (int i = 0; i < 3; i++)
				max_size[i] = max_size[i] * c;
		
		this.max_weight = (1 * 2 * c);
		
		if (c == 2)
			this.safety_rating *= (float) 0.99;
		else
			this.safety_rating *= (float) (0.40 * c);
		
		this.max_speed = (float) (80.0 - (20 *  c));
		this.max_distance = 150 + (400 * (c - 1));
	}

	public o_package(o_item __item, o_smartpost f, o_smartpost t, int c)
	{
		this.name = "Paketti: Esine: " + __item.name + ", Luokka: " + c;
		this.item = __item;
		this.__from = f;
		this.__to = t;
		this.__class = c;
		this.breakable = __item.breakable;
		this.safety_rating = __item.durability / 100;

		set_values(__class);
	}

	
	//holy quacamole, that's a lot of setters and getters.
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