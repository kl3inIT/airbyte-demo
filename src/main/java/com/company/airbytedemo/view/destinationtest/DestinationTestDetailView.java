package com.company.airbytedemo.view.destinationtest;

import com.company.airbytedemo.entity.DestinationTest;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "destination-tests/:id", layout = MainView.class)
@ViewController(id = "DestinationTest.detail")
@ViewDescriptor(path = "destination-test-detail-view.xml")
@EditedEntityContainer("destinationTestDc")
public class DestinationTestDetailView extends StandardDetailView<DestinationTest> {
    @Subscribe
    public void onInitEntity(final InitEntityEvent<DestinationTest> event) {
        switch (event.getEntity().getDestinationType()) {
            case S3 -> event.getEntity().getRawConfig().
        }

    }
}