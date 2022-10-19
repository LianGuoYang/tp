package seedu.address.ui;

import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import seedu.address.commons.util.CollectionUtil;

public class DataPanelsTabPaneManager {
    private final TabPane tabPane;
    private final Tab personTabPage;
    private final Tab groupTabPage;

    public DataPanelsTabPaneManager(TabPane pane, Tab personTabPage, Tab groupTabPage) {
        CollectionUtil.requireAllNonNull(pane, personTabPage, groupTabPage);
        tabPane = pane;
        this.personTabPage = personTabPage;
        this.groupTabPage = groupTabPage;
    }

    /**
     * Change the current tab to person tab.
     */
    public void changeToPatientTab() {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(personTabPage);
    }

    /**
     * Change the current tab to group tab.
     */
    public void changeToOngoingVisitTab() {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(groupTabPage);
    }
}
