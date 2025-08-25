package com.company.airbytedemo.view.destinationtest.fragment;

import com.company.airbytedemo.dto.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.tabsheet.JmixTabSheet;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.ViewComponent;
import org.springframework.beans.factory.annotation.Autowired;

@FragmentDescriptor("destination-s3-fragment.xml")
@RendererItemContainer("destinationDTODc")
public class DestinationS3Fragment extends FragmentRenderer<VerticalLayout, DestinationS3DTO> {

    @Autowired
    private DataManager dataManager;

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
    private InstanceContainer<DestinationS3DTO> destinationDTODc;
    @ViewComponent
    private InstanceContainer<DestinationS3CSVCommaSeparatedValuesDTO> destinationS3CSVDc;
    @ViewComponent
    private InstanceContainer<DestinationS3JSONLinesNewlineDelimitedJSONDTO> destinationS3JsonDc;
    @ViewComponent
    private InstanceContainer<DestinationS3AvroApacheAvroDTO> destinationS3ArvoDc;
    @ViewComponent
    private InstanceContainer<DestinationS3ParquetColumnarStorageDTO> destinationS3ParquetDc;

    public DestinationS3DTO getItem() {
        return destinationDTODc.getItemOrNull();
    }

    @Override
    public void setItem(DestinationS3DTO item) {
        formatTabSheet.setVisible(false);
        super.setItem(item);
        
        // Set item vào main container
        destinationDTODc.setItem(item);
        
        // Initialize format config containers
        initializeFormatContainers(item);
        
        // Set tab visibility based on current format
        if (item != null && item.getFormat() != null) {
            visibleByFormat(item.getFormat().toString());
        } else {
            formatTabSheet.setVisible(false);
        }
    }

    private void initializeFormatContainers(DestinationS3DTO item) {
        // Parse existing s3FormatConfig or create new instances
        S3FormatConfig existingConfig = item != null ? item.getS3FormatConfig() : null;
        
        // CSV
        DestinationS3CSVCommaSeparatedValuesDTO csvConfig = 
            (existingConfig instanceof DestinationS3CSVCommaSeparatedValuesDTO csv) 
                ? csv : dataManager.create(DestinationS3CSVCommaSeparatedValuesDTO.class);
        destinationS3CSVDc.setItem(csvConfig);

        // JSON
        DestinationS3JSONLinesNewlineDelimitedJSONDTO jsonConfig = 
            (existingConfig instanceof DestinationS3JSONLinesNewlineDelimitedJSONDTO json) 
                ? json : dataManager.create(DestinationS3JSONLinesNewlineDelimitedJSONDTO.class);
        destinationS3JsonDc.setItem(jsonConfig);

        // AVRO
        DestinationS3AvroApacheAvroDTO avroConfig = 
            (existingConfig instanceof DestinationS3AvroApacheAvroDTO avro) 
                ? avro : dataManager.create(DestinationS3AvroApacheAvroDTO.class);
        destinationS3ArvoDc.setItem(avroConfig);

        // PARQUET
        DestinationS3ParquetColumnarStorageDTO parquetConfig = 
            (existingConfig instanceof DestinationS3ParquetColumnarStorageDTO parquet) 
                ? parquet : dataManager.create(DestinationS3ParquetColumnarStorageDTO.class);
        destinationS3ParquetDc.setItem(parquetConfig);
    }

    @Subscribe(id = "destinationDTODc", target = Target.DATA_CONTAINER)
    public void onDestinationDTODcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<DestinationS3DTO> event) {
        if ("format".equals(event.getProperty())) {
            visibleByFormat(event.getValue() != null ? event.getValue().toString() : null);
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
                formatTabSheet.setSelectedTab(formatTabSheetCsvTab);
                break;
            case "JSON":
                formatTabSheetJsonTab.setVisible(true);
                formatTabSheet.setSelectedTab(formatTabSheetJsonTab);
                break;
            case "AVRO":
                formatTabSheetArvoTab.setVisible(true);
                formatTabSheet.setSelectedTab(formatTabSheetArvoTab);
                break;
            case "PARQUET":
                formatTabSheetParquetTab.setVisible(true);
                formatTabSheet.setSelectedTab(formatTabSheetParquetTab);
                break;
            default:
                formatTabSheet.setVisible(false);
                break;
        }
    }

    /**
     * Get current format config based on selected format
     */
    public S3FormatConfig getCurrentFormatConfig() {
        DestinationS3DTO item = getItem();
        if (item == null || item.getFormat() == null) {
            return null;
        }

        return switch (item.getFormat()) {
            case CSV -> destinationS3CSVDc.getItem();
            case JSON -> destinationS3JsonDc.getItem();
            case AVRO -> destinationS3ArvoDc.getItem();
            case PARQUET -> destinationS3ParquetDc.getItem();
            default -> null;
        };
    }

    /**
     * Update the main DTO with current format config
     */
    public void updateFormatConfig() {
        DestinationS3DTO item = getItem();
        if (item != null) {
            S3FormatConfig currentConfig = getCurrentFormatConfig();
            item.setS3FormatConfig(currentConfig);
        }
    }
}