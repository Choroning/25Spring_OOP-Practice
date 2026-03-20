/**
 * @file    Lab_InheritanceDemo.java
 * @brief Demonstrates inheritance, polymorphism, and method overriding
 *        with a Shape hierarchy (Shape -> Circle, Rectangle).
 * @author Cheolwon Park
 * @date 2026-03-21
 */
public class InheritanceDemo {

    /** Abstract base class for shapes. */
    static abstract class Shape {
        public abstract double area();

        @Override
        public String toString() {
            return getClass().getSimpleName() + " [area=" + String.format("%.2f", area()) + "]";
        }
    }

    /** A circle with a given radius. */
    static class Circle extends Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    /** A rectangle with given width and height. */
    static class Rectangle extends Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }
    }

    /**
     * Creates an array of shapes and demonstrates polymorphic behavior.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Circle(3.0),
            new Rectangle(10.0, 2.5)
        };

        for (Shape shape : shapes) {
            System.out.println(shape);

            if (shape instanceof Circle) {
                System.out.println("  -> This is a Circle.");
            } else if (shape instanceof Rectangle) {
                System.out.println("  -> This is a Rectangle.");
            }
        }
    }
}
