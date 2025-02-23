package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteTaskAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.GroupName;

/**
 * Parses input arguments and creates a new {@code DeleteTaskAllCommand} object
 */
public class DeleteTaskAllCommandParser implements Parser {
    /**
     * Parses the given {@code String} of arguments in the context of a {@code DeleteTaskAllCommand}
     * and returns a {@code DeleteTaskAllCommand} object for execution
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteTaskAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_TASK);

        GroupName inputGroup;
        Assignment inputTask;
        String group;

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_TASK) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskAllCommand.MESSAGE_USAGE));
        }

        try {
            group = argMultimap.getValue(PREFIX_GROUP).get();
            String task = argMultimap.getValue(PREFIX_TASK).get();

            inputGroup = ParserUtil.parseGroupName(group);
            inputTask = ParserUtil.parseAssignment(task);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }

        return new DeleteTaskAllCommand(group, inputTask);
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArguentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
