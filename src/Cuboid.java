public class Cuboid implements java.io.Serializable{
	private double length;
	private double height;
	private double width;
	public Cuboid(double length, double height, double width) {
		super();
		this.length = length;
		this.height = height;
		this.width = width;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
	
}
