package duke.logic.command.shopping;

import duke.model.commons.Item;
import duke.model.commons.Quantity;
import duke.model.inventory.Ingredient;

public class ShoppingCommandUtil {
    public static Item<Ingredient> createNewIngredient(Item<Ingredient> toEdit, ShoppingDescriptor edited) {
        assert toEdit != null;

        String name = edited.getName().orElse(toEdit.getItem().getName());
        Double quantity = edited.getQuantity().orElse(toEdit.getQuantity().getNumber());
        String remarks = edited.getRemarks().orElse(toEdit.getItem().getRemarks());
        Double unitCost = edited.getUnitCost().orElse(toEdit.getItem().getUnitPrice());

        return new Item<Ingredient>((new Ingredient(name, unitCost, remarks)), new Quantity(quantity));
    }

    public static Item<Ingredient> createNewIngredient(Item<Ingredient> toEdit, Double newQuantity) {
        assert toEdit != null;

        String name = toEdit.getItem().getName();
        Double quantity = newQuantity;
        String remarks = toEdit.getItem().getRemarks();
        Double unitCost = toEdit.getItem().getUnitPrice();

        return new Item<Ingredient>((new Ingredient(name, unitCost, remarks)), new Quantity(quantity));
    }
}
