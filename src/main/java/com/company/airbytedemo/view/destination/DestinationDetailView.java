package com.company.airbytedemo.view.destination;

import com.airbyte.api.models.shared.DestinationResponse;
import com.airbyte.api.models.shared.DestinationS3;
import com.company.airbytedemo.entity.Destination;
import com.company.airbytedemo.entity.DestinationS3DTO;
import com.company.airbytedemo.entity.DestinationType;
import com.company.airbytedemo.service.AirbyteService;
import com.company.airbytedemo.view.main.MainView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "destinations/:id", layout = MainView.class)
@ViewController(id = "Destination.detail")
@ViewDescriptor(path = "destination-detail-view.xml")
@EditedEntityContainer("destinationDc")
public class DestinationDetailView extends StandardDetailView<Destination> {
    @ViewComponent
    private JmixButton saveAndCloseButton;
    @ViewComponent
    private InstanceContainer<DestinationS3DTO> destinationS3DTODc;
    @Autowired
    private Notifications notifications;
    @Autowired
    private AirbyteService airbyteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Metadata metadata;

    @Subscribe(id = "saveAndCloseButton", subject = "singleClickListener")
    public void onSaveAndCloseButtonClick(final ClickEvent<JmixButton> event) {


        try {
            // Perform save operation
            notifications.create("Save successful")
                    .withType(Notifications.Type.SUCCESS)
                    .show();
        } catch (Exception e) {
            notifications.create("Save failed: " + e.getMessage())
                    .withType(Notifications.Type.ERROR)
                    .show();
        }
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (destinationS3DTODc.getItemOrNull() == null) {
            destinationS3DTODc.setItem(metadata.create(DestinationS3DTO.class));
        }
    }


    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        try {
        Destination destination = getEditedEntity();
        DestinationResponse res = airbyteService.createDestination(destination.getName(), destinationS3DTODc.getItem().getS3BucketPath());



            // Convert DestinationResponse thành JSON string
            String json = objectMapper.writeValueAsString(res);
            destination.setRawConfig(json);
            destination.setDesinationType(DestinationType.S3);
            dataManager.save(destination);
            notifications.create("Save successful")
                    .withType(Notifications.Type.SUCCESS)
                    .show();
        } catch (Exception e) {
            notifications.create("Save failed: " + e.getMessage())
                    .withType(Notifications.Type.ERROR)
                    .show();
            throw new RuntimeException("Serialize DestinationResponse failed", e);
        }

    }


}