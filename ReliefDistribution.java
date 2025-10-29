import java.util.*;

class Supply {
    String itemName;
    double itemWeight;
    double itemValue;
    boolean isDivisible;

    Supply(String itemName, double itemWeight, double itemValue, boolean isDivisible) {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.isDivisible = isDivisible;
    }

    double valuePerKg() {
        return itemValue / itemWeight;
    }
}

public class ReliefDistribution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Emergency Relief Supply Distribution");
        System.out.println();

        System.out.print("Enter the number of supply items: ");
        int totalItems = sc.nextInt();
        sc.nextLine();

        if (totalItems <= 0) {
            System.out.println("No items were entered. The process has been stopped.");
            sc.close();
            return;
        }

        List<Supply> supplies = new ArrayList<>();

        for (int i = 0; i < totalItems; i++) {
            System.out.println();
            System.out.println("Enter details for item " + (i + 1) + ":");

            System.out.print("Item name: ");
            String name = sc.nextLine();

            System.out.print("Weight in kilograms: ");
            double weight = sc.nextDouble();
            if (weight <= 0) {
                System.out.println("Weight must be greater than zero. Exiting...");
                sc.close();
                return;
            }

            System.out.print("Utility value (importance): ");
            double value = sc.nextDouble();
            if (value < 0) {
                System.out.println("Utility value cannot be negative. Exiting...");
                sc.close();
                return;
            }

            System.out.print("Is this item divisible? (1 for Yes, 0 for No): ");
            boolean divisible = sc.nextInt() == 1;
            sc.nextLine(); 

            supplies.add(new Supply(name, weight, value, divisible));
        }

        System.out.println();
        System.out.print("Enter the boat capacity in kilograms: ");
        double maxCapacity = sc.nextDouble();

        if (maxCapacity <= 0) {
            System.out.println("Boat capacity must be more than zero. Exiting...");
            sc.close();
            return;
        }

        long startTime = System.nanoTime();

      
        supplies.sort((a, b) -> Double.compare(b.valuePerKg(), a.valuePerKg()));

        double totalValue = 0.0;
        double remainingCapacity = maxCapacity;

        System.out.println();
        System.out.println("Loading the boat with the most valuable supplies first...");
        System.out.println();

        for (Supply s : supplies) {
            if (remainingCapacity <= 0) {
                break;
            }

            if (s.isDivisible) {
                double loadWeight = Math.min(s.itemWeight, remainingCapacity);
                double loadValue = s.valuePerKg() * loadWeight;
                totalValue += loadValue;
                remainingCapacity -= loadWeight;

                System.out.println("Loaded " + loadWeight + " kg of " + s.itemName);
                System.out.println("Utility value gained: " + loadValue);
                System.out.println();
            } else {
                if (s.itemWeight <= remainingCapacity) {
                    totalValue += s.itemValue;
                    remainingCapacity -= s.itemWeight;

                    System.out.println("Loaded full item: " + s.itemName + " (" + s.itemWeight + " kg)");
                    System.out.println("Utility value gained: " + s.itemValue);
                    System.out.println();
                } else {
                    System.out.println("Skipped " + s.itemName + " because it is too heavy for the remaining space.");
                    System.out.println();
                }
            }
        }

        System.out.println("Total utility value carried: " + totalValue);
        System.out.println("Remaining boat capacity: " + remainingCapacity + " kg");

        long endTime = System.nanoTime();
        double execTime = (endTime - startTime) / 1_000_000.0;
        System.out.println("Execution time: " + execTime + " milliseconds");

        System.out.println();
        System.out.println("Relief supplies are ready to be delivered.");

        sc.close();
    }
}
