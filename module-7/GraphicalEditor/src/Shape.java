public abstract class Shape {
    private final String shapeName;

    public Shape(String shapeName){
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }
}
