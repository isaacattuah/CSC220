package prog01;

/**
 * Class that represents a laptop computer.
 */
public class Laptop extends Computer {
    // Data Fields
    private double screenSize;
    private double weight;

    // Methods
    /**
     * Initializes a Laptop object with all properties specified.
     * @param man The computer manufacturer
     * @param proc The processor type
     * @param ram The RAM size
     * @param disk The disk size
     * @param procSpeed The processor speed
     * @param screen The screen size
     * @param wei The weight
     */
    public Laptop(String man, String proc, double ram, int disk,
            double procSpeed, double screen, double wei) {
        super(man, proc, ram, disk, procSpeed);
        screenSize = screen;
        weight = wei;
    }

    public String toString() {
      String result = super.toString() +
        "\nScreen Size: " + screenSize + " inches" +
        "\nWeight:  " + weight + " pounds";
      return result;
    }

  public double getScreenSize () {
    return screenSize;
  }

  public double getWeight () {
    return weight;
  }

  /** Special compute power method for Laptop.  
   * Because laptops overheat easily, their power rating is half of
   * a computer's.
   */
  
    public double computePower() {
      return getRamSize() * processorSpeed / 2;
    }
  
}
