package raster;

import model.Line;
import model.Point;
import model.Polygon;
import model.RectangularTriangle;

import java.util.List;

public abstract class LineRasterizer {
    Raster raster;
    public LineRasterizer(Raster raster){
        this.raster = raster;
    }

    public void rasterize(int x1, int y1, int x2, int y2){
        rasterize(x1, y1, x2, y2, 0xffffff);
    }

    public void rasterize(int x1, int y1, int x2, int y2, int color){

    }
    public void rasterizeDotted(int x1, int y1, int x2, int y2){

    }

    public void rasterize(Polygon polygon){
        List<Point> points = polygon.getPoints();
        for(int i = 0; i < points.size(); i++){
            int x1 = points.get(i).getX();
            int y1 = points.get(i).getY();
            int x2 = points.get((i+1)%points.size()).getX();
            int y2 = points.get((i+1)%points.size()).getY();
            rasterize(x1,y1,x2,y2);
        }
    }
    public void rasterizeDotted(Polygon polygon) {
        List<Point> points = polygon.getPoints();
        for (int i = 0; i < points.size(); i++) {
            int x1 = points.get(i).getX();
            int y1 = points.get(i).getY();
            int x2 = points.get((i + 1) % points.size()).getX();
            int y2 = points.get((i + 1) % points.size()).getY();
            rasterizeDotted(x1, y1, x2, y2);
        }
    }
    public void rasterize(RectangularTriangle rectangularTriangle) {
        List<Point> points = rectangularTriangle.getPoints();
        int x1 = 1;
        int x2 = 1;
        int y1 = 1;
        int y2 = 1;
        for (int i = 0; i <points.size(); i++)
        {
            x1 = points.get(i).getX();
            y1 = points.get(i).getY();
            x2 = points.get((i+1)%points.size()).getX();
            y2 = points.get((i+1)%points.size()).getY();
        }
        int x3 =1;
        int y3 = 1;
        x3 = (int) (Math.sqrt(Math.pow(x1,2)) - Math.sqrt(Math.pow(x2,2)));
        y3 = (int) (Math.sqrt(Math.pow(y1,2)) - Math.sqrt(Math.pow(y2,2)));
        rasterize(x1,x3,y1,y3);
        rasterize(x3,x2,y3,y2);
        rasterize(x1,x2,y2,y1);
    }

    public void rasterize(Line line){
        rasterize(line.getX1(),line.getY1(),line.getX2(),line.getY2(), line.getColor());
    }
}
