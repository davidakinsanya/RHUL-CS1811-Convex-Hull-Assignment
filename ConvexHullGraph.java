import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;

public class ConvexHullGraph extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        stage.setTitle("ConvexHullGraphic");
        int pointCount = ConvexHull.loadPoints(ConvexHull.maxPoints, ConvexHull.xVal, ConvexHull.yVal);
        if (ConvexHull.checkDuplicates(pointCount, ConvexHull.xVal, ConvexHull.yVal)) return;
        List<double[][]> arr = ConvexHull.computeConvexHull(pointCount, ConvexHull.xVal, ConvexHull.yVal);
        List<double[][]> arr2 = ConvexHull.notConvexPoints;

        Group root = new Group();
        Canvas canvas = new Canvas(700, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        plotConvexHull(gc, arr);
        plotNotConvex(gc, arr2);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void plotConvexHull(GraphicsContext gc, List<double[][]> arr) {
        for (int i = 0; i < arr.size(); i++) {
            double[][] elem = arr.get(i);
            gc.setStroke(Color.BLACK);
            gc.setFill(Color.GREEN);
            gc.fillOval(elem[0][0] * 16 - 5 + 30, elem[0][1] * 16 - 5 + 30, 10, 10);
            gc.fillOval(elem[1][0] * 16 - 5 + 30, elem[1][1] * 16 - 5 + 30, 10, 10);
            gc.setLineWidth(1);
            gc.strokeLine(elem[0][0] * 16 + 30, elem[0][1] * 16 + 30, elem[1][0] * 16 + 30, elem[1][1] * 16 + 30);

            gc.fillText(
                    "(" + Double.toString(elem[0][0]) + ", " + Double.toString(elem[0][1]) + ")",
                    elem[0][0] * 16 - 8 + 30,
                    elem[0][1] * 16 - 8 + 30);

            gc.fillText("(" + Double.toString(elem[1][0]) + ", " + Double.toString(elem[1][1]) + ")",
                    elem[1][0] * 16 - 8 + 30,
                    elem[1][1] * 16 - 8 + 30);
        }
    }

    public void plotNotConvex(GraphicsContext gc, List<double[][]> arr2) {
        for (int i = 0; i < arr2.size(); i++) {
            double[][] elem = arr2.get(i);

            gc.strokeOval(elem[0][0] * 16 - 5 + 30, elem[0][1] * 16 - 5 + 30, 10, 10);
            gc.strokeOval(elem[1][0] * 16 - 5 + 30, elem[1][1] * 16 - 5 + 30, 10, 10);

            gc.fillText(
                    "(" + Double.toString(elem[0][0]) + ", " + Double.toString(elem[0][1]) + ")",
                    elem[0][0] * 16 - 8 + 30,
                    elem[0][1] * 16 - 8 + 30);

            gc.fillText("(" + Double.toString(elem[1][0]) + ", " + Double.toString(elem[1][1]) + ")",
                    elem[1][0] * 16 - 8 + 30,
                    elem[1][1] * 16 - 8 + 30);
        }
    }
}
