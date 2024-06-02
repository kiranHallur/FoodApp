package food.moduels;

public class Restaurant {
	private int restaurantId;
	private String name;
	private String cuisineType;
	private int deliveryTime;
	private String address;
	private int adminUserId;
	private double rating;
	private boolean isactive;
	private String imagePath;
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Restaurant(int restaurantid, String name, String cisineType, int deliveryTime, String address,
			int adminUserId, double rating, boolean isactive, String imagePath) {
		super();
		this.restaurantId = restaurantid;
		this.name = name;
		this.cuisineType = cisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isactive = isactive;
		this.imagePath = imagePath;
	}
	public Restaurant( String name, String cisineType, int deliveryTime, String address,
			int adminUserId, double rating, boolean isactive, String imagePath) {
		super();
		
		this.name = name;
		this.cuisineType = cisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isactive = isactive;
		this.imagePath = imagePath;
	}
	public int getRestaurantid() {
		return restaurantId;
	}
	public void setRestaurantid(int restaurantid) {
		this.restaurantId = restaurantid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cisineType) {
		this.cuisineType = cisineType;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", cisineType=" + cuisineType
				+ ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminUserId=" + adminUserId
				+ ", rating=" + rating + ", isactive=" + isactive + ", imagePath=" + imagePath + "]";
	}
	

}
