package com.company.airbytedemo.view.destinationtest;

import com.company.airbytedemo.entity.DestinationTest;
import com.company.airbytedemo.entity.DestinationType;
import com.company.airbytedemo.view.databasesource.DatabaseSourceDetailView;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.view.*;
import org.checkerframework.checker.units.qual.t;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "destination-tests", layout = MainView.class)
@ViewController(id = "DestinationTest.list")
@ViewDescriptor(path = "destination-test-list-view.xml")
@LookupComponent("destinationTestsDataGrid")
@DialogMode(width = "64em")
public class DestinationTestListView extends StandardListView<DestinationTest> {

    @Autowired
    private ViewNavigators viewNavigators;
    @ViewComponent
    private DataGrid<DestinationTest> destinationTestsDataGrid;

    @Subscribe("destinationTestsDataGrid.createS3")
    public void onDestinationTestsDataGridCreateS3(final ActionPerformedEvent event) {
        String type = DestinationType.S3.getId();
        viewNavigators.detailView(destinationTestsDataGrid)
                .withViewClass(DestinationTestDetailView.class)
                .withQueryParameters(QueryParameters.of("type", type))
                .newEntity()
                .navigate();

    }



}