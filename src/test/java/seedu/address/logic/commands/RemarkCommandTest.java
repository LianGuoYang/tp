package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    void execute() {
        final Remark remark = new Remark("Some remark");
    }

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_SECOND_PERSON,
                new Remark(editedPerson.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_illegalArgumentForRemark_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        assertThrows(IllegalArgumentException.class,
                ()-> new PersonBuilder(firstPerson).withRemark("").build()
        );
    }

    @Test
    void execute_indexOutOfBounds_success() {
        assertThrows(IndexOutOfBoundsException.class,
                ()-> model.getFilteredPersonList().get(model.getFilteredPersonList().size() + 1));
    }

    @Test
    public void equals() {
        RemarkCommand addRemark1Command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark("no"));
        RemarkCommand addRemark2Command = new RemarkCommand(INDEX_SECOND_PERSON, new Remark("yes"));

        // same object -> returns true
        assertTrue(addRemark1Command.equals(addRemark1Command));

        // same values -> returns true
        RemarkCommand addRemark3Command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark("no"));
        assertTrue(addRemark1Command.equals(addRemark3Command));

        // different command -> returns false
        assertFalse(addRemark1Command.equals(addRemark2Command));

        // null -> returns false
        assertFalse(addRemark1Command.equals(null));
    }
}