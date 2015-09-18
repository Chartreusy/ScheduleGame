package model;

/**
 * Created by Chartreuse on 18/09/15.
 */
public class ItemList {
    // eventually want to generate these off of an english markhov chain

    // enum and array seems like a temp solution.
    public enum hItem{LOG, WATER, VINE, GLUE, NAIL};
    public static final Item[] harvest = new Item[]{
    new Item(0, "Log"),
    new Item(1, "Water"),
    new Item(2, "Vine"),
    new Item(3, "Glue") ,
    new Item(4, "Nail")
    };
    public enum sItem{PLANK, ROPE};
    public static final Item[] synth = new Item[]{
            new Item(1,"Plank"),
            new Item(2,"Rope"),
    };

}
