package duke.logic;

import duke.command.Command;
import duke.command.RedoCommand;
import duke.command.UndoCommand;
import duke.command.UndoableCommand;
import duke.commons.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<UndoableCommand> undoStack = new ArrayList<>();
    private List<UndoableCommand> redoStack = new ArrayList<>();
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public CommandManager(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void execute(Command command) throws DukeException {
        if (command instanceof UndoCommand) {
            undo();
        } else if (command instanceof RedoCommand) {
            redo();
        } else {
            command.execute(tasks, storage, ui);
            if (command instanceof UndoableCommand) undoStack.add((UndoableCommand) command);
        }
    }

    private void undo() throws DukeException {
        if (undoStack.size() == 0) {
            throw new DukeException("No task to be undone.");
        }
        undoStack.get(undoStack.size() - 1).undo(tasks, storage, ui);
        redoStack.add(undoStack.get(undoStack.size() - 1));
        undoStack.remove(undoStack.size() - 1);
    }

    private void redo() throws DukeException {
        if (redoStack.size() == 0) {
            throw new DukeException("No task to be redone.");
        }
        redoStack.get(redoStack.size() - 1).execute(tasks, storage, ui);
        redoStack.remove(redoStack.size() - 1);
    }
}