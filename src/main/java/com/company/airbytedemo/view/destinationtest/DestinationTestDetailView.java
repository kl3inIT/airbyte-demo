package com.company.airbytedemo.view.destinationtest;

import ch.qos.logback.core.util.StringUtil;
import com.airbyte.api.models.shared.DestinationS3;
import com.company.airbytedemo.dto.DestinationDTO;
import com.company.airbytedemo.dto.DestinationPosgresDTO;
import com.company.airbytedemo.dto.DestinationS3DTO;
import com.company.airbytedemo.entity.DestinationTest;
import com.company.airbytedemo.entity.DestinationType;
import com.company.airbytedemo.view.destinationtest.fragment.DestinationPostgresFragment;
import com.company.airbytedemo.view.destinationtest.fragment.DestinationS3Fragment;
import com.company.airbytedemo.view.main.MainView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.core.Metadata;
import io.jmix.core.MetadataTools;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Route(value = "destination-tests/:id", layout = MainView.class)
@ViewController(id = "DestinationTest.detail")
@ViewDescriptor(path = "destination-test-detail-view.xml")
@EditedEntityContainer("destinationTestDc")
public class DestinationTestDetailView extends StandardDetailView<DestinationTest> {

    String type = "";
    @ViewComponent
    private VerticalLayout destinationDetailVbox;
    @Autowired
    private Fragments fragments;

    @Autowired
    private Metadata metadata;

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        type = event.getQueryParameters().getSingleParameter("type").orElse(null);

//        if (StringUtils.hasLength(type)) {
//            DestinationType typeEnum = DestinationType.fromId(type);
//            DestinationTest entity = getEditedEntity();
//            entity.setDestinationType(typeEnum);
//            getEditedEntityContainer().setItem(entity);
//        }


    }


    @Subscribe
    public void onInitEntity(final InitEntityEvent<DestinationTest> event) {
        if (StringUtils.hasLength(type)) {
            DestinationType typeEnum = DestinationType.fromId(type);
            DestinationTest entity = event.getEntity();
            entity.setDestinationType(typeEnum);
            getEditedEntityContainer().setItem(entity);

            switch (typeEnum) {
                case S3:
                    DestinationS3DTO dtoS3 = metadata.create(DestinationS3DTO.class);
                    dtoS3.setId(UUID.randomUUID());
                    entity.setRawConfig(dtoS3);
                    break;
                case POSTGRES:
                    DestinationPosgresDTO dtoPostgres = metadata.create(DestinationPosgresDTO.class);
                    dtoPostgres.setId(UUID.randomUUID());
                    entity.setRawConfig(dtoPostgres);
                case MSSQL:
                default:
            }

        }
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        DestinationType typeEnum = getEditedEntity().getDestinationType();
        if (typeEnum != null) {
            switch (typeEnum) {
                case S3:
                    destinationDetailVbox.removeAll();
                    DestinationS3Fragment destinationS3Fragment = fragments.create(this, DestinationS3Fragment.class);
                    DestinationDTO s3Dto = getEditedEntity().getRawConfig();
                    destinationS3Fragment.setItem((DestinationS3DTO) s3Dto);
                    destinationDetailVbox.add(destinationS3Fragment);
                    break;
                case POSTGRES:
                    destinationDetailVbox.removeAll();
                    DestinationPostgresFragment destinationPostgresFragment = fragments.create(this, DestinationPostgresFragment.class);
                    DestinationDTO rawPostgres = getEditedEntity().getRawConfig();
                    destinationPostgresFragment.setItem((DestinationPosgresDTO) rawPostgres);
                    destinationDetailVbox.add(destinationPostgresFragment);
                    break;
                case MSSQL:
                default:
            }
        }

    }


}