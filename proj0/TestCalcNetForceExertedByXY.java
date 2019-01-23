import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Tests calcNetForceExertedByXY
 */
public class TestCalcNetForceExertedByXY {

    /**
     *  Tests calcNetForceExertedByXY.
     */
    public static void main(String[] args) {
        calcNetForceExertedByXY();
    }
    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Double expected
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     */
    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }
    /**
     *  Rounds a double value to a number of decimal places.
     *
     *  @param  value   Double to be rounded.
     *  @param  places  Integer number of places to round VALUE to.
     */
    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *  Checks the Body class to make sure calcNetForceExertedByXY works.
     */
    private static void calcNetForceExertedByXY() {
        System.out.println("Checking calcNetForceExertedByXY...");

        Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");

        Body b3 = new Body(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b4 = new Body(3.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Body[] bodies = {b2, b3, b4};

        double xNetForce = b1.calcNetForceExertedByX(bodies);
        double yNetForce = b1.calcNetForceExertedByY(bodies);

        checkEquals(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");

        System.out.println("Running test again, but with array that contains the target planet.");

        bodies = new Body[]{b1, b2, b3, b4};

        xNetForce = b1.calcNetForceExertedByX(bodies);
        yNetForce = b1.calcNetForceExertedByY(bodies);

        checkEquals(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");

    }
}
