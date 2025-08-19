package com.company.airbytedemo.view.filesource;

import com.airbyte.api.models.shared.File;
import com.airbyte.api.models.shared.SourceResponse;
import com.company.airbytedemo.entity.DatabaseSource;
import com.company.airbytedemo.entity.FileSource;
import com.company.airbytedemo.entity.SourceType;
import com.company.airbytedemo.service.AirbyteService;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "file-sources/:id", layout = MainView.class)
@ViewController(id = "FileSource.detail")
@ViewDescriptor(path = "file-source-detail-view.xml")
@EditedEntityContainer("fileSourceDc")
public class FileSourceDetailView extends StandardDetailView<FileSource> {

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
        FileSource fileSource = getEditedEntity();

        SourceResponse res = airbyteService.createFileSource(
                fileSource.getName(),
                fileSource.getDatasetName(),
                fileSource.getUrl(),
                fileSource.getFormat().getId(),
                fileSource.getProvider().getId()
        );

        fileSource.setSourceType(SourceType.FILE);
        fileSource.setAirbyteSourceId(res.sourceId());
        fileSource.setCreateAt(res.createdAt());
        fileSource.setDefinitionId(res.definitionId());
        fileSource.setWorkspaceId(res.workspaceId());
        dataManager.save(fileSource);

    }
}