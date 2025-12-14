public class Square extends Shape {
    private static final long serialVersionUID = 1L;

    private double side;

    public Square(String color, double side) {
        super(color);
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Square (Color=" + color + ", Side=" + side + ")";
    }
}
