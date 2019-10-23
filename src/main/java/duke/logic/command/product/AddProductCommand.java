package duke.logic.command.product;

import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.model.Model;
import duke.model.product.Product;

import static java.util.Objects.requireNonNull;

public class AddProductCommand extends ProductCommand {

    public static final String COMMAND_WORD = "add";
    public static String MESSAGE_SUCCESS = "New product: %s added" + System.lineSeparator() + "%s";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "Product with name \"%s\" already exists in the "
            + "product list";
    private final Product toAdd;

    public AddProductCommand(Product toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PRODUCT, toAdd.getProductName()));
        }

        String names = ProductCommandUtil.getNewIngredientsName(model, toAdd);
        model.addProduct(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getProductName(), names),
                CommandResult.DisplayedPage.PRODUCT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddProductCommand)
                && toAdd.equals(((AddProductCommand) other).toAdd);
    }
}
