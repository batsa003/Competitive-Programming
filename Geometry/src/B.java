import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 9/4/2015.
 */
public class B {
    static Point[] end1;
    static Point[] end2;
    static int X,Y;
    static Segment[] segs;
    static HashSet<Integer> vis;

    static int intersect(){// if object falls from X,Y. Return the index of the segment it intersects
        Point intersection= new Point(-1,-1);
        int ind=-1;
        Segment vert= new Segment(new Point(X,Y), (new Point(X,-10000)));
        for(int i=0; i<segs.length; i++){
            if( !vis.contains(i) && end1[i].x<=X  && X<= end2[i].x) {
                Point p = segs[i].intersect(vert,true);
                if (p != null && p.y > intersection.y) {
                    intersection = p;
                    ind = i;
                }
            }
        }
        return ind;
    }
    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        X= in.nextInt();
        int N= in.nextInt();
        Y=10001;
        end1= new Point[N];
        end2= new Point[N];
        for(int i=0; i<N; i++){
            end1[i]= new Point(in.nextInt(),in.nextInt());
            end2[i]= new Point(in.nextInt(),in.nextInt());
            if(end2[i].x<end1[i].x){
                Point temp= end1[i];
                end1[i]=end2[i];
                end2[i]=temp;
            }
        }
        segs=  new Segment[N];
        for(int i=0; i<N; i++) {
           segs[i] = new Segment(end1[i],end2[i]);
        }

        vis= new HashSet();
        while(true){
            int a= intersect();
            if(a==-1){
                System.out.println(X);
                return;
            }else{
                vis.add(a);
                if(end1[a].y<end2[a].y){
                    X= (int)end1[a].x;
                    Y=(int) end1[a].y;
                }else{
                    X= (int)end2[a].x;
                    Y=(int) end2[a].y;
                }

            }
        }
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


}

