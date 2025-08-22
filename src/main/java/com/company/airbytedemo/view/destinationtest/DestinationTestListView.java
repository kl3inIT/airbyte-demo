package com.company.airbytedemo.view.destinationtest;

import com.company.airbytedemo.entity.DestinationTest;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "destination-tests", layout = MainView.class)
@ViewController(id = "DestinationTest.list")
@ViewDescriptor(path = "destination-test-list-view.xml")
@LookupComponent("destinationTestsDataGrid")
@DialogMode(width = "64em")
public class DestinationTestListView extends StandardListView<DestinationTest> {

}