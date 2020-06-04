import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author yangshuai
 * @date 2020年06月04日14:11:58
 * <p>
 * NBody is the class that will actually run the simulation.
 */

public class NBody {

    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filePath) {
        In in = new In(filePath);
        int numberOfPlanets = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[numberOfPlanets];
        for (int i = 0; i < numberOfPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Body body = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            bodies[i] = body;
        }
        return bodies;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        Body[] bodies = readBodies(fileName);
        for (Body body : bodies) {
            body.draw();
        }
        StdDraw.show();

        for (int i = 0; i < T; ) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int j = 0; j < bodies.length; j++) {
                Body body = bodies[j];
                xForces[j] = body.calcNetForceExertedByX(bodies);
                yForces[j] = body.calcNetForceExertedByY(bodies);
            }

            for (int j = 0; j < bodies.length; j++) {
                Body body = bodies[j];
                body.update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body body : bodies) {
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            i += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }

}
