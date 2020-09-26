package prog01;

public class Main {
  public static void main (String[] args) {
    System.out.println("Set a breakpoint here and then single-step.");

    Computer c1 = new Computer("Dell", "Core 2", 2, 140, 2.40);
    Laptop n1 = new Laptop("Apple", "Core i7", 8, 500, 2.66, 15.0, 8.5);

    Computer c2 = n1; // O.K. because a Laptop is a Computer

    // The compiler doesn't ``know'' that c2 really contains a laptop.
   // Laptop n2 = c2; // compiler error

    // ``(Laptop) c2'' is a CAST, which is like a dynamic declaration.
    // We are telling the compiler that we are certain that c2
    // contains a Laptop at this point in the program.
    Laptop n3 = (Laptop) c2; // o.k., c2 references a Laptop

    // If we make a mistake, we won't find out until we run the program.
    //Laptop n4 = (Laptop) c1; // run time error

    System.out.println(c1 instanceof Laptop);
    System.out.println(c2 instanceof Laptop);
    System.out.println(n1 instanceof Computer);

    System.out.println(c1);
    System.out.println(n1);

    // Even though c2 is a Computer variable, this prints out the
    // information for Laptop.
    System.out.println(c2);

    System.out.println(n1.getWeight());

    // Even though n1 and c2 refer to the same thing, this is a
    // compiler error because the compiler doesn't ``know'' this.
    //System.out.println(c2.getWeight());

    // If we write a special computePower method for a Laptop, then
    // that gets called even if c2 is a Computer variable.
    System.out.println(c2.computePower());
  }
}
    
    
