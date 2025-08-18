package com.company.airbytedemo.view.databasesource;

import com.airbyte.api.models.shared.SourceResponse;
import com.company.airbytedemo.entity.DatabaseSource;
import com.company.airbytedemo.service.AirbyteService;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "database-sources/:id", layout = MainView.class)
@ViewController(id = "DatabaseSource.detail")
@ViewDescriptor(path = "database-source-detail-view.xml")
@EditedEntityContainer("databaseSourceDc")
public class DatabaseSourceDetailView extends StandardDetailView<DatabaseSource> {

    @Autowired
    private AirbyteService airbyteService;
    @Autowired
    private Notifications notifications;

    @Autowired
    private DataManager dataManager;

    @Subscribe(id = "saveAndCloseButton", subject = "clickListener")
    public void onSaveAndCloseButtonClick(final ClickEvent<JmixButton> event) {
        try {
            // Perform save operation
            saveEntity();
            notifications.create("Save successful")
                    .withType(Notifications.Type.SUCCESS)
                    .show();
        } catch (Exception e) {
            notifications.create("Save failed: " + e.getMessage())
                    .withType(Notifications.Type.ERROR)
                    .show();
        }
    }

    private void saveEntity() {
        DatabaseSource databaseSource = getEditedEntity();
        SourceResponse res = airbyteService.createDatabaseSource(
                databaseSource.getName(),
                databaseSource.getDbType().getId(),
                databaseSource.getDatabase(),
                databaseSource.getHost(),
                databaseSource.getPort(),
                databaseSource.getUsername(),
                databaseSource.getPassword()
        );

        databaseSource.setAirbyteSourceId(res.sourceId());
        databaseSource.setCreateAt(res.createdAt());
        databaseSource.setDefinitionId(res.definitionId());
        databaseSource.setWorkspaceId(res.workspaceId());
        dataManager.save(databaseSource);
    }

}