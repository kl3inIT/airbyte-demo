package com.company.airbytedemo.view.destination;

import com.airbyte.api.models.shared.DestinationS3;
import com.company.airbytedemo.entity.Destination;
import com.company.airbytedemo.entity.DestinationS3DTO;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

@Route(value = "destinations/:id", layout = MainView.class)
@ViewController(id = "Destination.detail")
@ViewDescriptor(path = "destination-detail-view.xml")
@EditedEntityContainer("destinationDc")
public class DestinationDetailView extends StandardDetailView<Destination> {
    @ViewComponent
    private JmixButton saveAndCloseButton;
    @ViewComponent
    private InstanceContainer<DestinationS3DTO> destinationS3DTODc;

    @Subscribe(id = "saveAndCloseButton", subject = "singleClickListener")
    public void onSaveAndCloseButtonClick(final ClickEvent<JmixButton> event) {
        Destination destination = getEditedEntity();
        DestinationS3DTO destinationS3DTO = destinationS3DTODc.getItem();

        var rawConfig = new DestinationS3();
    }


}