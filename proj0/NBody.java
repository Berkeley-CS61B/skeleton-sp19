public class NBody {

    public static double readRadius(String fileName) {
        In in;
        String line = "";
        try {
            in = new In(fileName);
            int secondline = 0;
            while (!in.isEmpty()) {
                if (secondline == 2) break;
                line = in.readLine();
                secondline++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Double.parseDouble(line);
    }

    public static Body getBody(String line) {
        String[] lineArr = line.split(" ");
        double[] doubleArr = new double[5];
        for (int i = 0; i < doubleArr.length - 1; i++) {
            doubleArr[i] = Double.parseDouble(lineArr[i]);
        }
        String photoName = lineArr[5];
        Body newBody = new Body(doubleArr[0], doubleArr[1], doubleArr[2],
                doubleArr[3], doubleArr[4], photoName);
        return newBody;
    }

    public static Body[] readBodies(String fileName) {
        int lineCount = 0;
        In in;
        String line = "";
        Body[] bodyList = new Body[5];

        try {
           in = new In(fileName);
           while (!in.isEmpty()) {
               if (lineCount > 1) {
                   line = in.readLine();
                   Body newBody = getBody(line);
                   bodyList[lineCount - 2] = newBody;
               }
               lineCount++;
           }
        } catch (Exception e) {
            System.out.println(e);
        }

        return bodyList;
    }
}