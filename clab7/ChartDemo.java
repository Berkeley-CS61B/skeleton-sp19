import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug. Demonstrated how to use the xchart library.
 */
public class ChartDemo {
    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int x = 0; x < 100; x += 1) {
            int thisY = x + r.nextInt(10);
            xValues.add(x);
            yValues.add(thisY);

            double thisY2 = 100*Math.sin(x);
            y2Values.add(thisY2);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("x + random(0, 10)", xValues, yValues);
        chart.addSeries("100*Math.sin(x)", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }
}
