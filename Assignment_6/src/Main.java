interface Animal{
    public void breath();
    public int getWeight();
}

interface Swimmable{
    public void swim();
}

interface Walkable{
    public void walk();
}

// all categories implements animal
abstract class Mammal implements Animal,Walkable{
    @Override
    public  void breath() {
        System.out.println("Mammal breathing.....");
    }

    @Override
    public abstract int getWeight();

    public abstract void growHair();

    @Override
    public void walk() {
        System.out.println("Mammal is walking...");
    }
}

abstract class Reptile implements Animal,Swimmable,Walkable{
    @Override
    public void breath() {
        System.out.println("Reptile breathing.....");
    }

    @Override
    public abstract int getWeight();

    public abstract void baskInSun();

    @Override
    public void swim() {
        System.out.println("Reptile is swimming...");
    }
    @Override
    public void walk() {
        System.out.println("Reptile is walking...");
    }
}

abstract class Fish implements Animal,Swimmable{
    @Override
    public void breath() {
        System.out.println("Fish breathing.....");
    }

    @Override
    public abstract int getWeight();

    public abstract void layEggs();

    @Override
    public void swim() {
        System.out.println("Fish is swimming...");
    }
}



// all subcategories extends categories
class Cow extends Mammal{
    private final int weight;

    public Cow(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void growHair()
    {
        System.out.println("Cow Here");
    }
}
class Camel extends Mammal{
    private final int weight;

    public Camel(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
    public void growHair()
    {
        System.out.println("Camel Here");
    }
}


class Turtle extends Reptile{
    private final int weight;
    public Turtle(int weight)
    {
        this.weight=weight;
    }

    public int getWeight() {
        return weight;
    }

    public  void baskInSun(){
        System.out.println("Turtle bask");
    }
}
class Crocodile extends Reptile{
    private final int weight;
    public Crocodile(int weight)
    {
        this.weight=weight;
    }

    public int getWeight() {
        return weight;
    }

    public  void baskInSun(){
        System.out.println("Crocodile bask");
    }
}

class Shark extends Fish{
    private final int weight;
    public Shark(int weight)
    {
        this.weight=weight;
    }

    public int getWeight() {
        return weight;
    }

    public  void layEggs(){
        System.out.println("Shark eggs");
    }
}
class Tuna extends Fish{
    private final int weight;
    public Tuna(int weight)
    {
        this.weight=weight;
    }

    public int getWeight() {
        return weight;
    }

    public  void layEggs(){
        System.out.println("Tuna eggs");
    }
}


//Main class
public class Main {
    public static void main(String[] args) {

        Cow cow = new Cow(100);
        Camel camel = new Camel(200);

        Turtle turtle = new Turtle(50);
        Crocodile crocodile = new Crocodile(100);

        Shark shark = new Shark(400);
        Tuna tuna = new Tuna(500);




        Animal[] animals = new Animal[]{cow, camel, turtle, crocodile, shark, tuna};
        System.out.println("------------------- Breathe Start -------------------");
        for(Animal animal : animals){
            animal.breath();
            System.out.println(animal.getWeight());
        }
        System.out.println("------------------ Breathe End ---------------------\n");



        Mammal[] mammals = new Mammal[]{cow, camel};
        System.out.println("------------------- Hair Start --------------------");
        for(Mammal mammal : mammals){
            mammal.growHair();
        }
        System.out.println("------------------- Hair End ----------------------\n");



        Reptile[] reptiles = new Reptile[]{turtle, crocodile};
        System.out.println("------------------- Bask Start --------------------");
        for(Reptile reptile : reptiles){
            reptile.baskInSun();
        }
        System.out.println("------------------- Bask End ----------------------\n");



        Fish[] fishes = new Fish[]{shark, tuna};
        System.out.println("------------------- Eggs Start --------------------");
        for(Fish fish : fishes){
            fish.layEggs();
        }
        System.out.println("------------------- Eggs End ----------------------\n");




        Swimmable[] swimmables = new Swimmable[]{turtle, crocodile, shark, tuna};
        System.out.println("------------------- Swim Start --------------------");
        for(Swimmable swimmable : swimmables){
            swimmable.swim();
        }
        System.out.println("------------------- Swim  End  --------------------\n");

        Walkable[] walkables = new Walkable[]{cow, camel, turtle, crocodile};
        System.out.println("------------------- Walk Start --------------------");
        for(Walkable walkable : walkables){
            walkable.walk();
        }
        System.out.println("------------------- Walk  End  --------------------\n");
    }
}