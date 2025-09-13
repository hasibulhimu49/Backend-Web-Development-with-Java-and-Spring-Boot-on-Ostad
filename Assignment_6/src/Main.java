public class Main{

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
            animal.breathe();
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