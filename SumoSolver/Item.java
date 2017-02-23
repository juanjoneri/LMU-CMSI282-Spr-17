public class Item {

    private static final String DEFAULT_LABEL = "Unbekannt";

    private String label; // may be unnecesary because instances have names.
    private int cost;
    private int weight;

    public Item (String label, int cost, int weight) {
        this.label = label;
        this.cost = cost;
        this.weight = weight;
    }

    public Item (int cost, int weight) {
        this(DEFAULT_LABEL, cost, weight);
    }

    public int getCost () {
        return this.cost;
    }

    public int getWeight () {
        return this.weight;
    }

    public String getLabel () {
        return this.label;
    }
}
