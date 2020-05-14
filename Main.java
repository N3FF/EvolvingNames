/**
 * TCSS 342 Data Structures. Assignment 2: Evolving Names.
 * 
 * @author Justin Neff
 *
 */

import java.util.Date;

public class Main {

  public static void main(String[] args) {
    // testGenome();
    // testPopulation();
    run();
  }

  private static void run() {
    long start = System.currentTimeMillis();
    Population gPop = new Population(100, .05);
    int i = 0;
    while (!gPop.matchFound()) {
      gPop.day();
      System.out.println(gPop.mostFitToString());
      i++;
    }
    System.out.println("Generations: " + i);
    System.out.println("Mutation Time: " + ((new Date()).getTime() - start) + "ms");
  }

  private static void testGenome() {
    Genome g = new Genome(.09);
    System.out.println("toString: " + g + "\nFitness: " + g.fitness() + "\nGetFirstChar: "
        + g.getChar(0) + "\nMutationRate: " + g.getMutationRate() + "\nSize: " + g.getSize());
    g.addChar(1, 'T');
    g.changeChar(0, 'V');
    System.out.println("Add and Change Char: " + g);
    g.deleteRandomChar();
    System.out.println("Delete Random Char: " + g);
    Genome g2 = new Genome(new Genome(.05));
    System.out.println("New Genome:" + g2);
    g2.crossover(g);
    g2.mutate();
    System.out.println("Crossover and Mutate:" + g2);
    System.out.println("G1 compare to G1:" + g.compareTo(g2));
  }

  private static void testPopulation() {
    Population p = new Population(25, .05);
    System.out.println("PopulationString: " + p + "\nMatch found: " + p.matchFound() + "\nMostFit: "
        + p.mostFitToString());
    p.day();
    p.day();
    p.day();
    System.out.println("\nAfter 3 days");
    System.out.println("PopulationString: " + p + "\nMatch found: " + p.matchFound()
        + "\nMostFit Info: [" + p.mostFitToString() + "]");
  }
}
