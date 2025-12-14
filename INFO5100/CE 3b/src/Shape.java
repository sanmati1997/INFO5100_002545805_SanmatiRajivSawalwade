import java.io.Serializable;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double calculateArea();

    // 🔥 This is the missing method!
    public abstract double calculatePerimeter();

    @Override
    public String toString() {
        return "Shape color = " + color;
    }
}
