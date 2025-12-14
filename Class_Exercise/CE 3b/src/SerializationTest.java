import java.io.*;
import java.util.ArrayList;

public class SerializationTest {

    public static void main(String[] args) {
        try {
            // Create some shapes
            ArrayList<Shape> shapes = new ArrayList<>();
            shapes.add(new Circle("Red", 5));
            shapes.add(new Rectangle("Blue", 4, 6));
            shapes.add(new Square("Green", 8));

            // Serialization
            FileOutputStream fileOut = new FileOutputStream("shapes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(shapes);
            out.close();
            fileOut.close();
            System.out.println("Shapes have been serialized to shapes.ser");

            // Deserialization
            FileInputStream fileIn = new FileInputStream("shapes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ArrayList<Shape> loadedShapes = (ArrayList<Shape>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("\nDeserialized Shapes:");

            for (Shape s : loadedShapes) {
                System.out.println(s.toString());
                System.out.println("Area: " + s.calculateArea());
                System.out.println("Perimeter: " + s.calculatePerimeter());
                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
