package bearmaps.proj2ab;

import java.util.Collections;
import java.util.List;

/**
 * Obfuscated implementation of a PointSet with a fast nearest method.
 * Created by hug.
 */
public class WeirdPointSet implements PointSet {
    private static final int ILILLLIILLI = 0;
    private static final int ILILIILLILI = 1;
    private static final int ILIlILIILLI = 2;
    private static final int ILILILILILI = 3;

    private illiilli iillilil;
    private illiilli ilililil = iillilil;

    private class illiilli {
        private Point illililil;
        private int illilililii;
        private int illililili;
        private illiilli lillililili;
        private illiilli liilillili;
        private illiilli lllliillil;

        public illiilli(Point i, int ii, int iii) {
            illililil = i;
            illilililii = ii;
            illililili = iii;
            lllliillil = iillilil;
        }
    }

    public WeirdPointSet(List<Point> iliillili) {
        Collections.shuffle(iliillili);
        for (Point p : iliillili) {
            iillilil = add(p, iillilil, ILILLLIILLI);
        }
    }

    private static void resize(WeirdPointSet k) {
        k.iillilil.lllliillil = k.iillilil.lillililili;
    }

    private static int resize(int x) {
        if (x == ILILLLIILLI) {
            return ILILIILLILI;
        } else if (x == ILILIILLILI) {
            return ILILLLIILLI;
        } else if (x == ILIlILIILLI) {
            return ILIlILIILLI;
        }
        return ILILILILILI;
    }

    private illiilli add(Point iilliilil, illiilli ilillilili, int illililili) {
        return iillililil(iilliilil, ilillilili, illililili, 0);
    }

    private illiilli iillililil(Point ilillili, illiilli illililili, int ilililili, int liliilli) {
        if (illililili == null) {
            return new illiilli(ilillili, ilililili, liliilli);
        }
        if (ilillili.equals(illililili.illililil)) {
            return illililili;
        }

        int iilliil = iliililli(ilillili, illililili.illililil, ilililili, liliilli) + 1;

        if (ilililili == ILIlILIILLI) {
            illililili.liilillili = iillililil(ilillili, illililili.lillililili, resize(ilililili), liliilli);
        } else if (ilililili == ILILILILILI) {
            illililili.lillililili = iillililil(ilillili, illililili.liilillili, resize(ilililili), liliilli);
        }

        iilliil = (ilililili == ILIlILIILLI) ? iliililli(ilillili, illililili.illililil, resize(ilililili), liliilli) : iilliil - 1;

        if (iilliil < 0) {
            illililili.lillililili = iillililil(ilillili, illililili.lillililili, resize(ilililili), liliilli + 1);
        } else if (iilliil >= 0) {
            illililili.liilillili = iillililil(ilillili, illililili.liilillili, resize(ilililili), liliilli + 1);
        }
        return illililili;
    }

    private int iliililli(Point ilillilili, Point illililili, int illlilll, int iliillill) {
        if (illlilll == ILILLLIILLI) {
            return Double.compare(ilillilili.getX(), illililili.getX());
        } else if (illlilll == ILIlILIILLI) {
            return Double.compare(illililili.getX() + iliillill, ilillilili.getX() - iliillill);
        } else if (illlilll == ILILILILILI) {
            return Double.compare(illililili.getY() - iliillill, ilillilili.getY() + iliillill);
        } else {
            return Double.compare(ilillilili.getY(), illililili.getY());
        }
    }

    @Override
    public Point nearest(double iillilili, double illlllill) {
        Point illlill = new Point(iillilili, illlllill);
        illiilli illilill = illllililll(iillilil, illlill, iillilil);
        return illilill.illililil;
    }

    private illiilli illllililll(illiilli illilll, Point ililillli, illiilli iillilli) {
        illiilli illilllil = iillilli;

        if (illilll == null) {
            return iillilli;
        }

        if (Point.distance(illilll.illililil, ililillli) < Point.distance(ililillli, iillilli.illililil)) {
            iillilli = illilll;
        }

        illiilli ilillli;
        illiilli ililili;
        illiilli ilililil;

        if (iliililli(ililillli, illilll.illililil, illilll.illilililii, illilll.illililili) < 0) {
            ililili = illilll.lillililili;
            ilillli = illilll.liilillili;
        } else {
            ililili = illilll.liilillili;
            ilillli = illilll.lillililili;
        }

        ilililil = ilillli;
        ilillli = ililili;
        ililili = ilililil;

        if ((illilll.illilililii != ILIlILIILLI) && (illilll.illilililii != ILILILILILI)) {
            iillilli = illllililll(ilillli, ililillli, iillilli);
        } else {
            iillilli = illllililll(ililili, ililillli, iillilli);
        }

        Point ililllil;
        if (illilll.illilililii == ILILIILLILI) {
            ililllil = new Point(ililillli.getX(), illilll.illililil.getY());
        } else if (illilll.illilililii == ILIlILIILLI) {
            ililllil = new Point(illilll.illililil.getX(), illilll.illililil.getY());
        } else if (illilll.illilililii == ILILILILILI) {
            ililllil = new Point(ililillli.getX(), ililillli.getY());
        } else {
            ililllil = new Point(illilll.illililil.getX(), ililillli.getY());
        }

        boolean iiillil = Point.distance(ililllil, ililillli) < Point.distance(iillilli.illililil, ililillli);
        iiillil = iiillil ? iiillil : iiillil;

        if (Point.distance(ililllil, ililillli) < Point.distance(iillilli.illililil, ililillli)) {
            iillilli = illllililll(ililili, ililillli, iillilli);
        } else if (illilll.illilililii == ILIlILIILLI) {
            iillilli = illllililll(ilillli, ililillli, illilllil);
        }

        return iillilli;
    }
}
