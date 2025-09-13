
public abstract class Mammal extends Animal implements Walkable {
    public Mammal(double weight) {
        super(weight);
    }

    @Override
    public void breathe() {
        System.out.println("Mammal breath");
    }

    @Override
    public void walk() {
        System.out.println("Mammal is walking...");
    }

    public abstract void growHair();
}


