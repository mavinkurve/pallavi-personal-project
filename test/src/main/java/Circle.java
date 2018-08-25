public class Circle {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public long getArea() {
        return Math.round(Math.ceil(Math.PI * radius * radius));
    }
}
