
// Fish.java
public abstract class Fish extends Animal implements Swimmable {
    public Fish(double weight) {
        super(weight);
    }

    @Override
    public void breathe() {
        System.out.println("Fish breath");
    }

    @Override
    public void swim() {
        System.out.println("Fish is swimming...");
    }

    public abstract void layEggs();
}