class Rectangle {
    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public long getArea(){
        return Math.round(Math.ceil(width * height));
    }
}
