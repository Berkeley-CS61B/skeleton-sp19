/**
 * @author yangshuai
 */

public class Body {

    public static final double G = 6.67e-11;
    /**
     * the current x position
     */
    double xxPos;

    /**
     * the current y position
     */
    double yyPos;

    /**
     * the current velocity(速度) in the x direction
     */
    double xxVel;

    /**
     * the current velocity in the y direction
     */
    double yyVel;

    /**
     * the mass重量
     */
    double mass;

    String imgFileName;

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(b.xxPos - xxPos, 2) + Math.pow(b.yyPos - yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        return G * mass * b.mass / Math.pow(r, 2);
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - xxPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F * dx / r;
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - yyPos;
        double F = calcForceExertedBy(b);
        double r = calcDistance(b);
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netForceExertedByX = 0D;
        for (Body body : bodies) {
            if (!body.equals(this)) {
                netForceExertedByX += calcForceExertedByX(body);
            }
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netForceExertedByY = 0D;
        for (Body body : bodies) {
            if (!body.equals(this)) {
                netForceExertedByY += calcForceExertedByY(body);
            }
        }
        return netForceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
