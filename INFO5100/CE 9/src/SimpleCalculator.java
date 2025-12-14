import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private TextField display = new TextField();
    private double firstNumber = 0;
    private String operator = "";

    @Override
    public void start(Stage stage) {

        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle("-fx-font-size: 32px; -fx-padding: 10;");

        GridPane keypad = new GridPane();
        keypad.setAlignment(Pos.CENTER);
        keypad.setHgap(10);
        keypad.setVgap(10);
        keypad.setPadding(new Insets(20));

        // Row 1
        addButton(keypad, "7", 0, 0);
        addButton(keypad, "8", 1, 0);
        addButton(keypad, "9", 2, 0);
        addOperatorButton(keypad, "÷", 3, 0);

        // Row 2
        addButton(keypad, "4", 0, 1);
        addButton(keypad, "5", 1, 1);
        addButton(keypad, "6", 2, 1);
        addOperatorButton(keypad, "×", 3, 1);

        // Row 3
        addButton(keypad, "1", 0, 2);
        addButton(keypad, "2", 1, 2);
        addButton(keypad, "3", 2, 2);
        addOperatorButton(keypad, "-", 3, 2);

        // Row 4
        Button clear = new Button("C");
        clear.setPrefSize(70, 70);
        clear.setStyle("-fx-font-size: 24px;");
        clear.setOnAction(e -> display.clear());
        keypad.add(clear, 0, 3);

        addButton(keypad, "0", 1, 3);
        addOperatorButton(keypad, "+", 2, 3);

        Button equals = new Button("=");
        equals.setPrefSize(70, 70);
        equals.setStyle("-fx-font-size: 24px;");
        equals.setOnAction(e -> calculate());
        keypad.add(equals, 3, 3);

        VBox root = new VBox(10, display, keypad);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 350, 500);

        stage.setTitle("Simple Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void addButton(GridPane grid, String value, int col, int row) {
        Button btn = new Button(value);
        btn.setPrefSize(70, 70);
        btn.setStyle("-fx-font-size: 24px;");
        btn.setOnAction(e -> display.appendText(value));
        grid.add(btn, col, row);
    }

    private void addOperatorButton(GridPane grid, String op, int col, int row) {
        Button btn = new Button(op);
        btn.setPrefSize(70, 70);
        btn.setStyle("-fx-font-size: 24px;");
        btn.setOnAction(e -> {
            firstNumber = Double.parseDouble(display.getText());
            operator = op;
            display.clear();
        });
        grid.add(btn, col, row);
    }

    private void calculate() {
        if (operator.isEmpty() || display.getText().isEmpty()) return;

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+": result = firstNumber + secondNumber; break;
            case "-": result = firstNumber - secondNumber; break;
            case "×": result = firstNumber * secondNumber; break;
            case "÷":
                if (secondNumber == 0) {
                    display.setText("Error");
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }

        display.setText(String.valueOf(result));
        operator = "";
    }

    public static void main(String[] args) {
        launch();
    }
}
