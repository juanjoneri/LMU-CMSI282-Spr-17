public class Item {

    private int cost;
    private int weight;

    public Item (int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }

    public int getCost () {
        return this.cost;
    }

    public int getWeight () {
        return this.weight;
    }

    public String toString () {
        String toPrint = "# Item: \n";
        toPrint += " - Cost: ";
        toPrint += this.cost;
        toPrint += "\n - Weight: ";
        toPrint += this.weight;
        return toPrint;
    }
}
