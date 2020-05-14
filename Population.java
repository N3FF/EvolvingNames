/**
 * TCSS 342 Data Structures. Assignment 2: Evolving Names.
 * 
 * @author Justin Neff
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
  private List<Genome> gList;
  private Genome mostFit;
  private static final Random RANDOM_NUM = new Random();

  Population(int numGnomes, double mutationRate) {
    gList = new ArrayList<>();
    for (int i = 0; i < numGnomes; i++) {
      gList.add(new Genome(mutationRate));
    }
    mostFit = gList.get(0);
  }

  public void day() {
    int size = gList.size();
    Collections.sort(gList);
    mostFit = gList.get(0);
    for (int i = 1; i < (size + 1) / 2; i++) {
      gList.remove(size - i);
    }

    while (gList.size() < size) {
      Genome g = new Genome(gList.get(RANDOM_NUM.nextInt(gList.size())));
      if (RANDOM_NUM.nextBoolean()) {
        g.mutate();
        gList.add(g);

      } else {
        g.crossover(gList.get(RANDOM_NUM.nextInt(gList.size())));
        g.mutate();
        gList.add(g);
      }
    }
  }

  public static int random(int largest) {
    return RANDOM_NUM.nextInt(largest);
  }

  public boolean matchFound() {
    return mostFit.fitness() == 0;
  }

  public String mostFitToString() {
    return "[Fitness: " + gList.get(0).fitness() + "] " + gList.get(0).toString();
  }

  public String toString() {
    Collections.sort(gList);
    return gList.toString();
  }
}
