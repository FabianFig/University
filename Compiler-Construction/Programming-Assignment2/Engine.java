
public abstract class Engine {

    public final static String[] COLORS = {
        "black", "red", "green", "blue", "yellow", "cyan", "magenta", "coral",
        "gray", "orange", "purple", "brown", "pink", "lime", "navy", "teal"
    };

    final protected double w, h;
    protected double x, y;
    protected double headingDeg;   // 0 = east, + is clockwise
    protected boolean penDown;
    protected int coloridx;
    protected int penWidth = 2;

    public void setPenWidth(int w) {
        penWidth = w;
    }

    protected Engine(double _w, double _h) {
        w = _w;
        h = _h;
        home();
    }

    public void home() {
        x = w / 2.0;
        y = h / 2.0;
        headingDeg = 0.0;
        penDown = true;
        coloridx = 0;
        penWidth = 2;
    }

    public void penDown() {
        penDown = true;
    }

    public void penUp() {
        penDown = false;
    }

    public void setColor(int c) {
        coloridx = c % COLORS.length;
    }

    public void rotate(double degrees) {
        headingDeg += degrees;
    }

    public void move(double dist) {
        double r = Math.toRadians(headingDeg);
        double nx = x + dist * Math.cos(r);
        double ny = y + dist * Math.sin(r);
        if (penDown) {
            draw_line(nx, ny);
        }
        x = nx;
        y = ny;
    }

    public void open() {
    }

    public void close() {
    }

    public void draw_line(double nx, double ny) {
    }

}
