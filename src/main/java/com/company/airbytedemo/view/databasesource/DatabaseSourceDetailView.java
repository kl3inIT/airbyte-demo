package com.company.airbytedemo.view.databasesource;

import com.company.airbytedemo.entity.DatabaseSource;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "database-sources/:id", layout = MainView.class)
@ViewController(id = "DatabaseSource.detail")
@ViewDescriptor(path = "database-source-detail-view.xml")
@EditedEntityContainer("databaseSourceDc")
public class DatabaseSourceDetailView extends StandardDetailView<DatabaseSource> {
}