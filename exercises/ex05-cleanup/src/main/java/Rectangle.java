/**
 * A rectangle with a width and height.
 */
public class Rectangle {
  private double width;
  private double height;

  /**
   * Creates a rectangle with the specified dimensions.
   *
   * @param width the rectangle's width
   * @param height the rectangle's height
   */
  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the area of this rectangle.
   *
   * @return the rectangle's area
   */
  public double area() {
    return width * height;
  }

  /**
   * Scales this rectangle's dimensions by the specified factor.
   *
   * @param factor the value by which to scale each dimension
   */
  public void scale(double factor) {
    width *= factor;
    height *= factor;
  }

  /**
   * Checks whether this rectangle has a greater area than another rectangle.
   *
   * @param other the rectangle to compare with
   * @return {@code true} if this rectangle has a greater area
   */
  public boolean isLargerThan(Rectangle other) {
    return this.area() > other.area();
  }
}
