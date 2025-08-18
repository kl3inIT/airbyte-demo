package com.company.airbytedemo.view.source;

import com.airbyte.api.models.shared.SourceResponse;
import com.company.airbytedemo.entity.Source;
import com.company.airbytedemo.entity.SourceType;
import com.company.airbytedemo.service.AirbyteService;
import com.company.airbytedemo.view.databasesource.DatabaseSourceDetailView;
import com.company.airbytedemo.view.main.MainView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Route(value = "sources", layout = MainView.class)
@ViewController(id = "Source.list")
@ViewDescriptor(path = "source-list-view.xml")
@LookupComponent("sourcesDataGrid")
@DialogMode(width = "64em")
public class SourceListView extends StandardListView<Source> {

    @Autowired
    private AirbyteService airbyteService;

    @ViewComponent
    private CollectionLoader<Source> sourcesDl;

    @ViewComponent
    private DataGrid<Source> sourcesDataGrid;

    @Autowired
    private ViewNavigators viewNavigators;

    @Autowired
    private DataManager dataManager;


    @Subscribe("sourcesDataGrid.createDb")
    public void onCreateDb(ActionPerformedEvent e) {
        viewNavigators.detailView(sourcesDataGrid)
                .withViewClass(DatabaseSourceDetailView.class)
                .newEntity()
                .navigate();
    }

    @Subscribe(id = "syncButton", subject = "clickListener")
    public void onSyncButtonClick(final ClickEvent<JmixButton> event) {
        List<SourceResponse> sources = airbyteService.getSources();

        for (SourceResponse src : sources) {
            // upsert entity dựa trên sourceId
            Source source = dataManager.load(Source.class)
                    .query("select e from Source e where e.airbyteSourceId = :id")
                    .parameter("id", src.sourceId())
                    .optional()
                    .orElseGet(() -> dataManager.create(Source.class));

            source.setAirbyteSourceId(src.sourceId());
            source.setName(src.name());
            source.setSouceType(SourceType.fromId(src.sourceType()));
            source.setCreateAt(src.createdAt());
            source.setDefinitionId(src.definitionId());
            source.setWorkspaceId(src.workspaceId());
            try {
                ObjectMapper om = new ObjectMapper();
                source.setRawConfiguration(om.writeValueAsString(src.configuration()));
            } catch (Exception ignore) {
            }
            dataManager.save(source);
        }
        sourcesDl.load();

    }


//    @Subscribe("sourcesDataGrid.createFile")
//    public void onCreateFile(ActionPerformedEvent e) {
//        viewNavigators.detailView(sourcesDataGrid)
//                .withViewClass(FileSourceDetailView.class)
//                .newEntity()
//                .navigate();
//    }
//
//    @Subscribe("sourcesDataGrid.createApi")
//    public void onCreateApi(ActionPerformedEvent e) {
//        viewNavigators.detailView(sourcesDataGrid)
//                .withViewClass(ApiSourceDetailView.class)
//                .newEntity()
//                .navigate();
//    }
}