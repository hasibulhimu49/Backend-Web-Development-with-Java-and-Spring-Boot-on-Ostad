
public class Tuna extends Fish {
    public Tuna(double weight) {
        super(weight);
    }

    @Override
    public void layEggs() {
        System.out.println("Tuna eggs");
    }
}