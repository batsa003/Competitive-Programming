import java.util.*;

/**
 * Created by Bat-Orgil on 9/4/2015.
 */
public class C {
    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    //http://paulbourke.net/geometry/polygonmesh/PolygonUtilities.java
    //http://www.mathopenref.com/coordpolygonarea2.html


    public static double area(Point[] polyPoints) {
        int i, j, n = polyPoints.length;
        double area = 0;

        for (i = 0; i < n; i++) {
            j = (i + 1) % n;
            area += polyPoints[i].x * polyPoints[j].y;
            area -= polyPoints[j].x * polyPoints[i].y;
        }
        area /= 2.0;
        return (area);
    }

    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        int N= in.nextInt();
        Point[] vertices= new Point[N];
        for(int i=0; i<N; i++){
            vertices[i]= new Point(in.nextInt(),in.nextInt());
        }

        long boundarypoints=0;
        for(int i=0; i<N; i++){
            int j= (i+1)%N;
            boundarypoints+= gcd((long)(vertices[i].x-vertices[j].x), (long)(vertices[i].y-vertices[j].y));
        }
        long area= (long) Math.ceil(area(vertices));
        if(area<0) area= -area;
        long ans= (area+1- boundarypoints/2);
        System.out.println(ans);
    }

    static class Segment {
        public final Point a;
        public final Point b;
        private double distance = Double.NaN;
        private Line line = null;

        public Segment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        public double length() {
            if (Double.isNaN(distance))
                distance = a.distance(b);
            return distance;
        }

        public double distance(Point point) {
            double length = length();
            double left = point.distance(a);
            if (length < GeometryUtils.epsilon)
                return left;
            double right = point.distance(b);
            if (left * left > right * right + length * length)
                return right;
            if (right * right > left * left + length * length)
                return left;
            return point.distance(line());
        }

        public Point intersect(Segment other, boolean includeEnds) {
            Line line = line();
            Line otherLine = other.a.line(other.b);
            if (line.parallel(otherLine))
                return null;
            Point intersection = line.intersect(otherLine);
            if (contains(intersection, includeEnds) && other.contains(intersection, includeEnds))
                return intersection;
            else
                return null;
        }

        public boolean contains(Point point, boolean includeEnds) {
            if (a.equals(point) || b.equals(point))
                return includeEnds;
            if (a.equals(b))
                return false;
            Line line = line();
            if (!line.contains(point))
                return false;
            Line perpendicular = line.perpendicular(a);
            double aValue = perpendicular.value(a);
            double bValue = perpendicular.value(b);
            double pointValue = perpendicular.value(point);
            return aValue < pointValue && pointValue < bValue || bValue < pointValue && pointValue < aValue;
        }

        public Line line() {
            if (line == null)
                line = a.line(b);
            return line;
        }

        public Point middle() {
            return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
        }

        public Point intersect(Line line) {
            Line selfLine = line();
            Point intersect = selfLine.intersect(line);
            if (intersect == null)
                return null;
            if (contains(intersect, true))
                return intersect;
            return null;
        }

        public double distance(Segment other) {
            Line line = line();
            Line otherLine = other.line();
            Point p = line == null || otherLine == null ? null : line.intersect(otherLine);
            if (p != null && contains(p, true) && other.contains(p, true))
                return 0;
            return Math.min(Math.min(other.distance(a), other.distance(b)), Math.min(distance(other.a), distance(other.b)));
        }
    }


    static class Point {
        public static final Point ORIGIN = new Point(0, 0);
        public final double x;
        public final double y;

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Line line(Point other) {
            if (equals(other))
                return null;
            double a = other.y - y;
            double b = x - other.x;
            double c = -a * x - b * y;
            return new Line(a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Point point = (Point) o;

            return Math.abs(x - point.x) <= GeometryUtils.epsilon && Math.abs(y - point.y) <= GeometryUtils.epsilon;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
            result = (int) (temp ^ (temp >>> 32));
            temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        public double distance(Point other) {
            return GeometryUtils.fastHypot(x - other.x, y - other.y);
        }

        public double distance(Line line) {
            return Math.abs(line.a * x + line.b * y + line.c);
        }

        public double value() {
            return GeometryUtils.fastHypot(x, y);
        }

        public double angle() {
            return Math.atan2(y, x);
        }


        public Point rotate(double angle) {
            double nx = x * Math.cos(angle) - y * Math.sin(angle);
            double ny = y * Math.cos(angle) + x * Math.sin(angle);
            return new Point(nx, ny);
        }
    }


    static class Line {
        public final double a;
        public final double b;
        public final double c;

        public Line(Point p, double angle) {
            a = Math.sin(angle);
            b = -Math.cos(angle);
            c = -p.x * a - p.y * b;
        }

        public Line(double a, double b, double c) {
            double h = GeometryUtils.fastHypot(a, b);
            this.a = a / h;
            this.b = b / h;
            this.c = c / h;
        }

        public Point intersect(Line other) {
            if (parallel(other))
                return null;
            double determinant = b * other.a - a * other.b;
            double x = (c * other.b - b * other.c) / determinant;
            double y = (a * other.c - c * other.a) / determinant;
            return new Point(x, y);
        }

        public boolean parallel(Line other) {
            return Math.abs(a * other.b - b * other.a) < GeometryUtils.epsilon;
        }

        public boolean contains(Point point) {
            return Math.abs(value(point)) < GeometryUtils.epsilon;
        }

        public Line perpendicular(Point point) {
            return new Line(-b, a, b * point.x - a * point.y);
        }

        public double value(Point point) {
            return a * point.x + b * point.y + c;
        }

        public double distance(Point center) {
            return Math.abs(value(center));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (!parallel(line)) return false;
            if (Math.abs(a * line.c - c * line.a) > GeometryUtils.epsilon || Math.abs(b * line.c - c * line.b) > GeometryUtils.epsilon) return false;

            return true;
        }
    }

    static class GeometryUtils {
        public static double epsilon = 1e-8;

        public static double fastHypot(double...x) {
            if (x.length == 0)
                return 0;
            else if (x.length == 1)
                return Math.abs(x[0]);
            else {
                double sumSquares = 0;
                for (double value : x)
                    sumSquares += value * value;
                return Math.sqrt(sumSquares);
            }
        }

        public static double fastHypot(double x, double y) {
            return Math.sqrt(x * x + y * y);
        }

        public static double fastHypot(double[] x, double[] y) {
            if (x.length == 0)
                return 0;
            else if (x.length == 1)
                return Math.abs(x[0]- y[0]);
            else {
                double sumSquares = 0;
                for (int i = 0; i < x.length; i++) {
                    double diff = x[i] - y[i];
                    sumSquares += diff * diff;
                }
                return Math.sqrt(sumSquares);
            }
        }

        public static double fastHypot(int[] x, int[] y) {
            if (x.length == 0)
                return 0;
            else if (x.length == 1)
                return Math.abs(x[0]- y[0]);
            else {
                double sumSquares = 0;
                for (int i = 0; i < x.length; i++) {
                    double diff = x[i] - y[i];
                    sumSquares += diff * diff;
                }
                return Math.sqrt(sumSquares);
            }
        }

        public static double missileTrajectoryLength(double v, double angle, double g) {
            return (v * v * Math.sin(2 * angle)) / g;
        }

        public static double sphereVolume(double radius) {
            return 4 * Math.PI * radius * radius * radius / 3;
        }

        public static double triangleSquare(double first, double second, double third) {
            double p = (first + second + third) / 2;
            return Math.sqrt(p * (p - first) * (p - second) * (p - third));
        }

        public static double canonicalAngle(double angle) {
            while (angle > Math.PI)
                angle -= 2 * Math.PI;
            while (angle < -Math.PI)
                angle += 2 * Math.PI;
            return angle;
        }

        public static double positiveAngle(double angle) {
            while (angle > 2 * Math.PI - GeometryUtils.epsilon)
                angle -= 2 * Math.PI;
            while (angle < -GeometryUtils.epsilon)
                angle += 2 * Math.PI;
            return angle;
        }
    }
    static class Polygon {
        public final Point[] vertices;
        private Segment[] sides;

        public Polygon(Point...vertices) {
            this.vertices = vertices.clone();
        }

        public double square() {
            double sum = 0;
            for (int i = 1; i < vertices.length; i++)
                sum += (vertices[i].x - vertices[i - 1].x) * (vertices[i].y + vertices[i - 1].y);
            sum += (vertices[0].x - vertices[vertices.length - 1].x) * (vertices[0].y + vertices[vertices.length - 1].y);
            return Math.abs(sum) / 2;
        }

        public Point center() {
            double sx = 0;
            double sy = 0;
            for (Point point : vertices) {
                sx += point.x;
                sy += point.y;
            }
            return new Point(sx / vertices.length, sy / vertices.length);
        }

        public static boolean over(Point a, Point b, Point c) {
            return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) < -GeometryUtils.epsilon;
        }

        private static boolean under(Point a, Point b, Point c) {
            return a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y) > GeometryUtils.epsilon;
        }

        public static Polygon convexHull(Point[] points) {
            if (points.length == 1)
                return new Polygon(points);
            Arrays.sort(points, new Comparator<Point>() {
                public int compare(Point o1, Point o2) {
                    int value = Double.compare(o1.x, o2.x);
                    if (value != 0)
                        return value;
                    return Double.compare(o1.y, o2.y);
                }
            });
            Point left = points[0];
            Point right = points[points.length - 1];
            List<Point> up = new ArrayList<Point>();
            List<Point> down = new ArrayList<Point>();
            for (Point point : points) {
                if (point == left || point == right || !under(left, point, right)) {
                    while (up.size() >= 2 && under(up.get(up.size() - 2), up.get(up.size() - 1), point))
                        up.remove(up.size() - 1);
                    up.add(point);
                }
                if (point == left || point == right || !over(left, point, right)) {
                    while (down.size() >= 2 && over(down.get(down.size() - 2), down.get(down.size() - 1), point))
                        down.remove(down.size() - 1);
                    down.add(point);
                }
            }
            Point[] result = new Point[up.size() + down.size() - 2];
            int index = 0;
            for (Point point : up)
                result[index++] = point;
            for (int i = down.size() - 2; i > 0; i--)
                result[index++] = down.get(i);
            return new Polygon(result);
        }

        public boolean contains(Point point) {
            return contains(point, false);
        }

        public boolean contains(Point point, boolean strict) {
            for (Segment segment : sides()) {
                if (segment.contains(point, true))
                    return !strict;
            }
            double totalAngle = GeometryUtils.canonicalAngle(Math.atan2(vertices[0].y - point.y, vertices[0].x - point.x) -
                    Math.atan2(vertices[vertices.length - 1].y - point.y, vertices[vertices.length - 1].x - point.x));
            for (int i = 1; i < vertices.length; i++) {
                totalAngle += GeometryUtils.canonicalAngle(Math.atan2(vertices[i].y - point.y, vertices[i].x - point.x) -
                        Math.atan2(vertices[i - 1].y - point.y, vertices[i - 1].x - point.x));
            }
            return Math.abs(totalAngle) > Math.PI;
        }

        public Segment[] sides() {
            if (sides == null) {
                sides = new Segment[vertices.length];
                for (int i = 0; i < vertices.length - 1; i++)
                    sides[i] = new Segment(vertices[i], vertices[i + 1]);
                sides[sides.length - 1] = new Segment(vertices[vertices.length - 1], vertices[0]);
            }
            return sides;
        }

        public static double triangleSquare(Point a, Point b, Point c) {
            return Math.abs((a.x - b.x) * (a.y + b.y) + (b.x - c.x) * (b.y + c.y) + (c.x - a.x) * (c.y + a.y)) / 2;
        }

        public double perimeter() {
            double result = vertices[0].distance(vertices[vertices.length - 1]);
            for (int i = 1; i < vertices.length; i++)
                result += vertices[i].distance(vertices[i - 1]);
            return result;
        }
    }

}
