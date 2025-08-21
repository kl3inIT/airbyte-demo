package com.company.airbytedemo.view.destination;

import com.company.airbytedemo.dto.*;
import com.company.airbytedemo.entity.Destination;
import com.company.airbytedemo.service.AirbyteService;
import com.company.airbytedemo.utils.DestinationS3Parser;
import com.company.airbytedemo.view.main.MainView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "destinations/:id", layout = MainView.class)
@ViewController(id = "Destination.detail")
@ViewDescriptor(path = "destination-detail-view.xml")
@EditedEntityContainer("destinationDc")
public class DestinationDetailView extends StandardDetailView<Destination> {

    @Autowired
    protected DataManager dataManager;
    @ViewComponent
    private InstanceContainer<Destination> destinationDc;

    @ViewComponent
    private InstanceContainer<DestinationS3DTO> destinationS3DTODc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Notifications notifications;
    @Autowired
    private AirbyteService airbyteService;
    @ViewComponent
    private JmixTabSheet formatTabSheet;
    @ViewComponent("formatTabSheet.csvTab")
    private Tab formatTabSheetCsvTab;
    @ViewComponent("formatTabSheet.jsonTab")
    private Tab formatTabSheetJsonTab;
    @ViewComponent("formatTabSheet.arvoTab")
    private Tab formatTabSheetArvoTab;
    @ViewComponent("formatTabSheet.parquetTab")
    private Tab formatTabSheetParquetTab;
    @ViewComponent
    private InstanceContainer<DestinationS3CSVCommaSeparatedValuesDTO> destinationS3CSVDc;
    @ViewComponent
    private InstanceContainer<DestinationS3JSONLinesNewlineDelimitedJSONDTO> destinationS3JsonDc;
    @ViewComponent
    private InstanceContainer<DestinationS3AvroApacheAvroDTO> destinationS3ArvoDc;
    @ViewComponent
    private InstanceContainer<DestinationS3ParquetColumnarStorageDTO> destinationS3ParquetDc;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Destination entity = destinationDc.getItemOrNull();
        formatTabSheet.setVisible(false);
        try {
            var parsed = DestinationS3Parser.parse(
                    entity != null ? entity.getRawConfig() : null,
                    objectMapper,
                    dataManager
            );

            // root
            destinationS3DTODc.setItem(parsed.root());

            // 4 DTO con (nếu null thì tạo rỗng để form binding không lỗi)
            destinationS3CSVDc.setItem(parsed.csv() != null
                    ? parsed.csv()
                    : dataManager.create(DestinationS3CSVCommaSeparatedValuesDTO.class));

            destinationS3JsonDc.setItem(parsed.json() != null
                    ? parsed.json()
                    : dataManager.create(DestinationS3JSONLinesNewlineDelimitedJSONDTO.class));

            destinationS3ArvoDc.setItem(parsed.avro() != null
                    ? parsed.avro()
                    : dataManager.create(DestinationS3AvroApacheAvroDTO.class));

            destinationS3ParquetDc.setItem(parsed.parquet() != null
                    ? parsed.parquet()
                    : dataManager.create(DestinationS3ParquetColumnarStorageDTO.class));


        } catch (Exception ex) {
            notifications.create("Cannot parse S3 config JSON: " + ex.getMessage())
                    .withType(Notifications.Type.ERROR).show();

            // fallback rỗng
            destinationS3DTODc.setItem(dataManager.create(DestinationS3DTO.class));
            destinationS3CSVDc.setItem(dataManager.create(DestinationS3CSVCommaSeparatedValuesDTO.class));
            destinationS3JsonDc.setItem(dataManager.create(DestinationS3JSONLinesNewlineDelimitedJSONDTO.class));
            destinationS3ArvoDc.setItem(dataManager.create(DestinationS3AvroApacheAvroDTO.class));
            destinationS3ParquetDc.setItem(dataManager.create(DestinationS3ParquetColumnarStorageDTO.class));
        }

    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        try {
            Destination destination = getEditedEntity();
            DestinationS3DTO s3DTO = destinationS3DTODc.getItem();
//            airbyteService.createDestination()

        } catch (Exception e) {
            notifications.create("Save failed: " + e.getMessage())
                    .withType(Notifications.Type.ERROR)
                    .show();
            event.preventSave();
        }
    }

    @Subscribe(id = "destinationS3DTODc", target = Target.DATA_CONTAINER)
    public void onDestinationS3DTODcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<DestinationS3DTO> event) {
        if ("format".equals(event.getProperty())) {
            visibleByFormat(event.getValue().toString());
        }
    }

    private void visibleByFormat(String format) {
        // Ẩn hết các tab trước
        formatTabSheetCsvTab.setVisible(false);
        formatTabSheetJsonTab.setVisible(false);
        formatTabSheetArvoTab.setVisible(false);
        formatTabSheetParquetTab.setVisible(false);

        if (format == null) {
            formatTabSheet.setVisible(false);
            return;
        }

        formatTabSheet.setVisible(true);

        switch (format.toUpperCase()) {
            case "CSV":
                formatTabSheetCsvTab.setVisible(true);
                formatTabSheetJsonTab.setVisible(false);
                formatTabSheetArvoTab.setVisible(false);
                formatTabSheetParquetTab.setVisible(false);
                formatTabSheet.setSelectedTab(formatTabSheetCsvTab);
                break;
            case "JSON":
                formatTabSheetJsonTab.setVisible(true);
                formatTabSheetCsvTab.setVisible(false);
                formatTabSheetArvoTab.setVisible(false);
                formatTabSheetParquetTab.setVisible(false);
                formatTabSheet.setSelectedTab(formatTabSheetJsonTab);
                break;
            case "AVRO":
                formatTabSheetArvoTab.setVisible(true);
                formatTabSheetCsvTab.setVisible(false);
                formatTabSheetJsonTab.setVisible(false);
                formatTabSheetParquetTab.setVisible(false);
                formatTabSheet.setSelectedTab(formatTabSheetArvoTab);
                break;
            case "PARQUET":
                formatTabSheetParquetTab.setVisible(true);
                formatTabSheetArvoTab.setVisible(false);
                formatTabSheetCsvTab.setVisible(false);
                formatTabSheetJsonTab.setVisible(false);
                formatTabSheet.setSelectedTab(formatTabSheetParquetTab);
                break;
            default:
                formatTabSheet.setVisible(false);
                break;
        }
    }


}