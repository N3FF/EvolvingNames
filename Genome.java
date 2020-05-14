/**
 * TCSS 342 Data Structures. Assignment 2: Evolving Names.
 * 
 * @author Justin Neff
 *
 */

public class Genome implements Comparable<Genome> {

  private String target = "JUSTIN NEFF UW";
  private StringBuilder gString;
  private double mutationRate = 0.05;
  private char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
      'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '­', '’'};

  Genome(double mutationRate) {
    this.mutationRate = mutationRate;
    gString = new StringBuilder("A");
  }

  Genome(Genome gene) {
    gString = new StringBuilder(gene.toString());
    this.mutationRate = gene.getMutationRate();
  }

  public void mutate() {
    if (Population.random(100) < mutationRate * 100) {
      addChar(Population.random(gString.length()), chars[Population.random(chars.length - 1)]);
    }

    if (Population.random(100) < mutationRate * 100) {
      if (gString.length() > 1) {
        deleteRandomChar();
      }
    }

    for (int i = 0; i < gString.length(); i++) {
      if (Population.random(100) < mutationRate * 100) {
        gString.setCharAt(i, chars[Population.random(chars.length - 1)]);
      }
    }
  }

  public void crossover(Genome other) {
    StringBuilder sb = new StringBuilder();
    int gStringSize = gString.length();
    int otherSize = other.getSize();
    int size = (gStringSize < otherSize) ? otherSize : gStringSize;

    for (int i = 0; i < size; i++) {
      Genome random = (Population.random(1) == 0) ? this : other;
      if (random.getSize() > i)
        sb.append(random.getChar(i));
      else
        break;
    }
    gString = sb;
  }

  public Integer fitness() {
    int n = gString.length();
    int m = target.length();
    int f = Math.abs(m - n);
    int min = Math.min(n, m);
    for (int i = 0; i < min; i++) {
      if (gString.charAt(i) != target.charAt(i)) {
        f++;
      }
    }
    return f += Math.abs(m - n);
  }

  public double getMutationRate() {
    return mutationRate;
  }

  public char getChar(int index) {
    return gString.charAt(index);
  }

  public int getSize() {
    return gString.length();
  }

  public void addChar(int index, char newChar) {
    gString.insert(index, newChar);
  }

  public void deleteRandomChar() {
    gString.deleteCharAt(Population.random(gString.length()));
  }

  public void changeChar(int index, char newChar) {
    gString.setCharAt(index, newChar);
  }

  @Override
  public String toString() {
    return gString.toString();
  }

  @Override
  public int compareTo(Genome o) {
    if (o.fitness() < this.fitness()) {
      return 1;
    } else if (o.fitness() > this.fitness()) {
      return -1;
    } else {
      return 0;
    }
  }
}
