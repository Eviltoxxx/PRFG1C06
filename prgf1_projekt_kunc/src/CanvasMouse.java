
import model.Point;
import model.Polygon;
import raster.LineRasterizer;
import raster.LineRasterizerBI;
import raster.Raster;
import raster.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.VK_C;

public class CanvasMouse {

    private JPanel panel;
    private Raster raster;
	private LineRasterizer lineRasterizer;
	private final Polygon polygon = new Polygon();
	private Point point;
	private final List<Point> points = new ArrayList<>();
    private int x, y;
    static JLabel obj1 = new JLabel();

    public CanvasMouse(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        raster = new RasterBufferedImage(width, height);
		lineRasterizer = new LineRasterizerBI(raster);



        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    raster.setPixel(e.getX(), e.getY(), 0xffff00);
                    x = e.getX();
                    y = e.getY();

                    point = new Point(x,y);
                    polygon.addPoint(point);
                    lineRasterizer.rasterize(polygon);
                }
                if (e.getButton() == MouseEvent.BUTTON2)
                    raster.setPixel(e.getX(), e.getY(), 0xffff00);
                    if (points.size() == 0)
                    {
                        point = new Point(x,y);
                    }

                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    raster.setPixel(e.getX(), e.getY(), 0xffff00);
                }
                clear();
                redraw();
            }

			@Override
			public void mouseReleased(MouseEvent e) {
                lineRasterizer.rasterize(polygon);
                panel.repaint();
                points.add(point);
                System.out.println("Polozky: "+ points.size());
			}
		});

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                clear();
                point.setX(e.getX());
                point.setY(e.getY());
                polygon.addPoint(point);
                //lineRasterizer.rasterize(polygon);
                lineRasterizer.rasterizeDotted(polygon);
                redraw();
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == VK_C) {
                    clear();
                }
            }
        });

    }

    public void clear() {
        raster.clear();
    }

    void redraw(){
        for (Point point: points) {
            lineRasterizer.rasterize(polygon);
        }
        panel.repaint();
    }

    public void present(Graphics graphics) {
        BufferedImage img = ((RasterBufferedImage) raster).getImg();
        graphics.drawImage(img, 0, 0, null);
    }

    public void start() {
        clear();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CanvasMouse(800, 600).start());
    }

}
