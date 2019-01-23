/**
 *  Tests Nbody.readBodies. Reads in ./data/planets.txt and checks output of
 *  readBodies().
 */
public class TestReadBodies {

    private static boolean doubleEquals(double actual, double expected, double eps) {
        return Math.abs(expected - actual) <= eps * Math.max(expected, actual);
    }

    /** Checks to make sure that readBodies() works perfectly. */
    private static String checkReadBodys() {
        System.out.println("Checking readBodies...");
        String planetsTxtPath = "./data/planets.txt";
        /* If the following line fails to compile, you probably need to make
         * a certain method static! */
        Body[] actualOutput = NBody.readBodies(planetsTxtPath);

        /* Check the simple things: */
        if (actualOutput == null) {
            return "FAIL: readBodies(); null output";
        }
        if (actualOutput.length != 5) {
            return "FAIL: readBodies().length: Expected 5 and you gave " + actualOutput.length;
        }

        /* Check to make sure every planet exists, plus random spot checks */
        boolean foundEarth = false;
        boolean foundMars = false;
        boolean foundMercury = false;
        boolean foundSun = false;
        boolean foundVenus = false;
        boolean randomChecksOkay = true;
        for (Body p : actualOutput) {
            if ("earth.gif".equals(p.imgFileName)) {
                foundEarth = true;
                if (!doubleEquals(p.xxPos, 1.4960e+11, 0.01)) {
                    System.out.println("Advice: Your Earth doesn't have the right xxPos!");
                    randomChecksOkay = false;
                }
            } else if ("mars.gif".equals(p.imgFileName)) {
                foundMars = true;
            } else if ("mercury.gif".equals(p.imgFileName)) {
                foundMercury = true;
                if (!doubleEquals(p.yyPos, 0, 0.01)) {
                    System.out.println("Advice: Your Mercury doesn't have the right yyPos!");
                    randomChecksOkay = false;
                }
            } else if ("sun.gif".equals(p.imgFileName)) {
                foundSun = true;
            } else if ("venus.gif".equals(p.imgFileName)) {
                foundVenus = true;
                if (!doubleEquals(p.mass, 4.8690e+24, 0.01)) {
                    System.out.println("Advice: Your Venus doesn't have the right mass!");
                    randomChecksOkay = false;
                }
            }
        }

        /* Build up a nice list of missing planets */
        String missingBodys = "";
        if (!foundEarth) {
            missingBodys += "Earth, ";
        }
        if (!foundMars) {
            missingBodys += "Mars, ";
        }
        if (!foundMercury) {
            missingBodys += "Mercury, ";
        }
        if (!foundSun) {
            missingBodys += "Sun, ";
        }
        if (!foundVenus) {
            missingBodys += "Venus, ";
        }
        if (missingBodys.length() > 0) {
            String answer = "FAIL: readBodies(); Missing these planets: ";
            answer += missingBodys.substring(0, missingBodys.length() - 2);
            return answer;
        }
        if (!randomChecksOkay) {
            return "FAIL: readBodies(); Not all planets have correct info!";
        }
        return "PASS: readBodies(); Congrats! This was the hardest test!";
    }

    public static void main(String[] args) {
        String testResult = checkReadBodys();
        System.out.println(testResult);
    }
}
