abstract class Shape {
    // Static field shared by all shapes
    static String defaultColor = "Blue";

    // Abstract methods -> abstraction
    abstract double calculateArea();
    abstract double calculatePerimeter();

    // Concrete method
    void displayColor() {
        System.out.println("Color: " + defaultColor);
    }
}

//------------------- Triangle -------------------
class Triangle extends Shape {
    double a, b, c; // sides

    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double calculateArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    double calculatePerimeter() {
        return a + b + c;
    }
}

//------------------- Rectangle -------------------
class Rectangle extends Shape {
    double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

//------------------- Square -------------------
class Square extends Shape {
    double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    double calculateArea() {
        return side * side;
    }

    @Override
    double calculatePerimeter() {
        return 4 * side;
    }
}

//------------------- Circle -------------------
class Circle extends Shape {
    double radius;

    static String className = "Circle";  // Example of another static field

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

//------------------- Main Class -------------------
public class ShapeDemo {
    public static void main(String[] args) {

        // Polymorphism: Parent class reference = Child class objects
        Shape s1 = new Triangle(3, 4, 5);
        Shape s2 = new Rectangle(4, 6);
        Shape s3 = new Square(5);
        Shape s4 = new Circle(7);

        Shape[] shapes = { s1, s2, s3, s4 };

        for (Shape shape : shapes) {
            System.out.println("---------------------------");
            System.out.println("Class: " + shape.getClass().getSimpleName());
            shape.displayColor(); // Static color shared
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
        }

        // Demonstrating static field
        System.out.println("\nStatic Field Example:");
        System.out.println("Default Color = " + Shape.defaultColor);
        System.out.println("Circle className = " + Circle.className);
    }
}
