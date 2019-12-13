import java.util.*;

class ConvexHull {
    public static int maxPoints = 70;
    public static double[] xVal = new double[maxPoints];
    public static double[] yVal = new double[maxPoints];
    public static List<double[][]> notConvexPoints = new ArrayList<>();

    static int loadPoints(int maxPoints, double[] xVal, double[] yVal) {
        System.out.print("Enter any double: ");
        Scanner sc = new Scanner(System.in);
        Double scanned_sc = sc.nextDouble();

        int no_of_read_points = 0;
        int numPoints = 0;
        int xVal_index = 0;
        int yVal_index = 0;

        while (scanned_sc >= 0.0) {
            no_of_read_points++;

            if (no_of_read_points <= 2 * maxPoints) {

                if (no_of_read_points % 2 == 0.0) {
                    yVal[yVal_index] = scanned_sc;
                    yVal_index++;
                    numPoints++;
                } else {
                    xVal[xVal_index] = scanned_sc;
                    xVal_index++;
                }

                if (no_of_read_points < 2 * maxPoints) {
                    System.out.print("Enter any double: ");
                    sc = new Scanner(System.in);
                    scanned_sc = sc.nextDouble();
                }

            } else {
                System.out.println("\nThe maximum capacity of the array has been reached!\n");
                scanned_sc = -1.0;
            }
        }
        return numPoints;
    }

    static boolean checkDuplicates(int pointCount, double[] xVal, double[] yVal) {
        for (int i = pointCount - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if ((xVal[i] == xVal[j] || yVal[i] == yVal[j])) {
                    System.out.println("Duplicate found..! ");
                    return true;
                }
            }
        }
        return false;
    }

    static List<double[][]> computeConvexHull(int pointCount, double[] xVal, double[] yVal) {
        List<double[][]> convexPoints = new ArrayList<>();

        for (int i = pointCount - 1; i >= 1; i--) {
            for (int j = 0; j < pointCount; j++) {
                double m, c;
                int above = 0, below = 0;
                double[] pointI = {xVal[i], yVal[i]};
                double[] pointJ = {xVal[j], yVal[j]};

                if (pointI[0] != pointJ[0]) {
                    m = (pointJ[1] - pointI[1]) / (pointJ[0] - pointI[0]);
                    c = pointI[1] - m * pointI[0];

                    if (m != Double.POSITIVE_INFINITY && m != Double.NEGATIVE_INFINITY) {
                        for (int k = 0; k < pointCount; k++) {
                            double[] pointK = {xVal[k], yVal[k]};

                            if (pointK[1] > m * pointK[0] + c) {
                                above++;
                            } else if (pointK[1] < m * pointK[0] + c) {
                                below++;
                            }
                        }
                    }

                    if ((above > 0 && below == 0) || (above == 0 && below > 0)) {
                        System.out.print("Line connecting the points " + Arrays.toString(pointI));
                        System.out.println(" and " + Arrays.toString(pointJ) + " inna di convex hull");
                        double[][] points = {pointI, pointJ};
                        convexPoints.add(points);
                    } else {
                        double[][] points = {pointI, pointJ};
                        notConvexPoints.add(points);
                    }
                }
            }
        }
        return convexPoints;
    }
}
