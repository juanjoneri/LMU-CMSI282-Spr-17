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
        StringBuilder sb = new StringBuilder("$");
        sb.append(this.cost);
        sb.append(" / ");
        sb.append(this.weight);
        sb.append(" pounds");
        return sb.toString();
    }
}
