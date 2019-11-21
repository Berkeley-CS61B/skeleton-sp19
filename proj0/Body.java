public class Body {
    static final double G = 6.67e-11;

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public Body(double xxPos, double yyPos, double xxVel,
                double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public double calcDistance(Body d) {
        double dx = this.xxPos - d.xxPos;
        double dy = this.yyPos - d.yyPos;
        dx = Math.pow(dx, 2);
        dy = Math.pow(dy, 2);
        return Math.sqrt(dx + dy);
    }

    public double calcForceExertedBy(Body d) {
        double r = this.calcDistance(d);
        return (G * this.mass * d.mass) / (Math.pow(r, 2));
    }

    public double calcForceExertedByX(Body d) {
        double F = this.calcForceExertedBy(d);
        double dx = this.xxPos - d.xxPos;
        double r = this.calcDistance(d);
        double xforce = (F * dx) / r;
        return -xforce;
    }

    public double calcForceExertedByY(Body d) {
        double F = this.calcForceExertedBy(d);
        double dy = this.yyPos - d.yyPos;
        double r = this.calcDistance(d);
        double yforce = (F * dy) / r;
        return -yforce;
    }

    public void update(double dt, double fx, double fy) {
       double xaccleration = fx / this.mass;
       double yacceleration = fy / this.mass;
       this.xxVel = this.xxVel + (dt * xaccleration);
       this.yyVel = this.yyVel + (dt * yacceleration);
       this.xxPos = this.xxPos + (dt * this.xxVel);
       this.yyPos = this.yyPos + (dt * this.yyVel);
    }

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
}