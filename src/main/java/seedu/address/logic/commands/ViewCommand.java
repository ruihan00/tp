package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRAPH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.EntryType;
import seedu.address.model.entry.GraphType;
import seedu.address.model.entry.Month;

/**
 * View income/expenditure entries to the application.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Show graphically all %s by %s";
    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": View income/expenditure entries to PennyWise. " + "Parameters: " + PREFIX_TYPE + "TYPE " + "["
        + PREFIX_GRAPH + "GRAPH]\n" + "Example: " + COMMAND_WORD + " " + PREFIX_TYPE + "e " + PREFIX_GRAPH + "c ";

    private static final String ENTRY_EXPENDITURE = "expenditures";
    private static final String ENTRY_INCOME = "income";
    private static final String GRAPH_CATEGORY = "category";
    private static final String GRAPH_MONTH = "month";

    private final EntryType entryType;
    private final Month month;
    private final GraphType graphType;

    /**
     * Creates a ViewCommand to view the specified {@code entryType}.
     */
    public ViewCommand(EntryType entryType, GraphType graphType) {
        requireNonNull(entryType);
        this.entryType = entryType;
        this.month = null;
        this.graphType = graphType;
    }

    /**
     * Creates a ViewCommand to view the specified {@code entryType} at the specified {@code month}.
     */
    public ViewCommand(EntryType entryType, Month month, GraphType graphType) {
        requireNonNull(entryType);
        requireNonNull(month);
        this.entryType = entryType;
        this.month = month;
        this.graphType = graphType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        System.out.println("graph type in execute: " + graphType.getGraphType().toString());
        switch (entryType.getEntryType()) {
        case EXPENDITURE:
            switch (graphType.getGraphType()) {
            case CATEGORY:
                System.out.println("[ViewCommand] Show all exp by categories");
                return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_EXPENDITURE, GRAPH_CATEGORY), false,
                                         false, true);
            case MONTH:
                System.out.println("[ViewCommand] Show all exp by month");
                return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_EXPENDITURE, GRAPH_MONTH), false,
                        false, true);
            default:
                //should never reach here
                return null;
            }

        case INCOME:
            switch (graphType.getGraphType()) {
            case CATEGORY:
                System.out.println("[ViewCommand] Show all income by categories");
                return new CommandResult(String.format(MESSAGE_SUCCESS, ENTRY_INCOME, GRAPH_CATEGORY), false, false,
                                         true);
            case MONTH:
                // TODO: Add month
                return null;
            default:
                //should never reach here
                return null;
            }
        default:
            //should never reach here
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ViewCommand)) {
            return false;
        }
        ViewCommand otherViewCommand = (ViewCommand) other;
        if (this.month == null) {
            return this.entryType.equals(otherViewCommand.entryType);
        }
        return this.entryType.equals(otherViewCommand.entryType)
                && this.month.equals(otherViewCommand.month)
                && this.graphType.equals(otherViewCommand.graphType);
    }
}
