public abstract class Animal {
    private final double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public abstract void breathe();
}
