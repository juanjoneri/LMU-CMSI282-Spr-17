import java.util.concurrent.ThreadLocalRandom;

public class Item {

    private long id; // to ensure no repetitions in the cart
    private int cost;
    private int weight;

    public Item (int cost, int weight) {
        this.id = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE); //hopefully the store will not have too many items
        this.cost = cost;
        this.weight = weight;
    }

    public long getId () {
        return this.id;
    }

    public int getCost () {
        return this.cost;
    }

    public int getWeight () {
        return this.weight;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder("$");
        sb.append(this.cost);
        sb.append(" / ");
        sb.append(this.weight);
        sb.append(" pounds");
        return sb.toString();
    }
}
