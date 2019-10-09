package duke.logic.parser.inventory;

import duke.logic.command.inventory.AddInventoryCommand;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.ArgumentTokenizer;
import duke.logic.parser.commons.Parser;
import duke.logic.parser.exceptions.ParseException;
import duke.model.commons.Ingredient;

import static duke.logic.parser.commons.CliSyntax.*;

public class AddInventoryCommandParser implements Parser<AddInventoryCommand> {

    @Override
    public AddInventoryCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_INVENTORY_NAME,
                PREFIX_INVENTORY_QUANTITY,
                PREFIX_INVENTORY_UNIT,
                PREFIX_INVENTORY_INDEX
        );

        Ingredient ingredient = new Ingredient(
                map.getValue(PREFIX_INVENTORY_NAME).orElse(""),
                map.getValue(PREFIX_INVENTORY_QUANTITY).orElse(String.valueOf(0)),
                map.getValue(PREFIX_INVENTORY_UNIT).orElse(String.valueOf(0))
        );
        return new AddInventoryCommand(ingredient);
    }
}