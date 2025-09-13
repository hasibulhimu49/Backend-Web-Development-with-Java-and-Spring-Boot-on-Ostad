
public abstract class Reptile extends Animal implements Walkable, Swimmable {
    public Reptile(double weight) {
        super(weight);
    }

    @Override
    public void breathe() {
        System.out.println("Reptile breath");
    }

    @Override
    public void walk() {
        System.out.println("Reptile is walking...");
    }

    @Override
    public void swim() {
        System.out.println("Reptile is swimming...");
    }

    public abstract void baskInSun();
}