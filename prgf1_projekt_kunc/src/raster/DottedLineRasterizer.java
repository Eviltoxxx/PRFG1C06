package raster;

public class DottedLineRasterizer extends LineRasterizer {
    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    public void rasterizeDotted(int x1, int y1, int x2, int y2) {
        float dx;
        float dy;
        float len;
        int x;
        int y;
        float xi;
        float yi;

        int i;

        dx = Math.abs(x2 - x1);

        dy = Math.abs(y2 - y1);


        if (dx >= dy)

            len = dx;

        else

            len = dy;


        dx = (x2 - x1) / len;

        dy = (y2 - y1) / len;


        x = (int) (x1 + 0.5);

        y = (int) (y1 + 0.5);

        raster.setPixel(x1, y1, 3);

        raster.setPixel(x2, y2, 3);

        for (i = 0; i <= len; i++) {
            if (i % 9 < 2) {
            } else if (i % 9 < 6)

                raster.setPixel(x, y, 3);

            else if (i % 9 == 7) {
            } else

                raster.setPixel(x, y, 3);

            x += dx;

            y += dy;
        }
    }
}
