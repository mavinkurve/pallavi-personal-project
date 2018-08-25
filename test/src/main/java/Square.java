class Square {
    double width;

    public Square(double width) {
        this.width = width;
    }

    public long getArea() {
        return Math.round(Math.ceil(width * width));
    }
}
