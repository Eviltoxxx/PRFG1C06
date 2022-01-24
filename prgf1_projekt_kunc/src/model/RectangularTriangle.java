package model;

import java.util.ArrayList;
import java.util.List;

public class RectangularTriangle {
    private List<Point> points;
    public RectangularTriangle() {
        points = new ArrayList<>(3);
    }
    public void addPoint(Point point) {
        points.add(point);
    }
    public List<Point> getPoints() {
        return points;
    }
}
