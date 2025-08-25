package com.company.airbytedemo.view.destinationtest;

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
import io.jmix.core.EntitySerialization;
import io.jmix.core.Metadata;
import io.jmix.flowui.Fragments;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Route(value = "destination-tests/:id", layout = MainView.class)
@ViewController(id = "DestinationTest.detail")
@ViewDescriptor(path = "destination-test-detail-view.xml")
@EditedEntityContainer("destinationTestDc")
public class DestinationTestDetailView extends StandardDetailView<DestinationTest> {

    private static final Logger log = LoggerFactory.getLogger(DestinationTestDetailView.class);
    private String type = "";

    @ViewComponent
    private VerticalLayout destinationDetailVbox;

    @Autowired
    private Fragments fragments;
    @Autowired
    private Metadata metadata;
    @Autowired
    private EntitySerialization entitySerialization;

    // giữ reference fragment để lấy DTO khi lưu
    private DestinationS3Fragment currentS3Fragment;
    private DestinationPostgresFragment currentPostgresFragment;

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        type = event.getQueryParameters().getSingleParameter("type").orElse(null);
    }

    @Subscribe
    public void onInitEntity(final InitEntityEvent<DestinationTest> event) {
        if (StringUtils.hasLength(type)) {
            DestinationType typeEnum = DestinationType.fromId(type);
            DestinationTest entity = event.getEntity();
            entity.setDestinationType(typeEnum);

            switch (typeEnum) {
                case S3 -> {
                    DestinationS3DTO dto = metadata.create(DestinationS3DTO.class);
                    if (dto.getId() == null) dto.setId(UUID.randomUUID());
                    entity.setRawConfig(dto);
                }
                case POSTGRES -> {
                    DestinationPosgresDTO dto = metadata.create(DestinationPosgresDTO.class);
                    if (dto.getId() == null) dto.setId(UUID.randomUUID());
                    entity.setRawConfig(dto);
                }
                default -> {
                }
            }
        }
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        destinationDetailVbox.removeAll();
        currentS3Fragment = null;
        currentPostgresFragment = null;

        DestinationType typeEnum = getEditedEntity().getDestinationType();
        if (typeEnum == null) return;

        switch (typeEnum) {
            case S3 -> {
                DestinationS3Fragment fragment = fragments.create(this, DestinationS3Fragment.class);
                DestinationDTO any = getEditedEntity().getRawConfig();
                DestinationS3DTO dto = (any instanceof DestinationS3DTO d)
                        ? d : metadata.create(DestinationS3DTO.class);
                if (dto.getId() == null) dto.setId(UUID.randomUUID());
                if (!(any instanceof DestinationS3DTO)) getEditedEntity().setRawConfig(dto);

                fragment.setItem(dto);
                currentS3Fragment = fragment;
                destinationDetailVbox.add(fragment);
            }
            case POSTGRES -> {
                DestinationPostgresFragment fragment = fragments.create(this, DestinationPostgresFragment.class);
                DestinationDTO any = getEditedEntity().getRawConfig();
                DestinationPosgresDTO dto = (any instanceof DestinationPosgresDTO d)
                        ? d : metadata.create(DestinationPosgresDTO.class);
                if (dto.getId() == null) dto.setId(UUID.randomUUID());
                if (!(any instanceof DestinationPosgresDTO)) getEditedEntity().setRawConfig(dto);

                fragment.setItem(dto);
                currentPostgresFragment = fragment;
                destinationDetailVbox.add(fragment);
            }
            default -> {
            }
        }
    }

    @Subscribe
    public void onBeforeSave(final BeforeSaveEvent event) {
        DestinationTest e = getEditedEntity();
        DestinationType typeEnum = e.getDestinationType();
        if (typeEnum == null) return;

        switch (typeEnum) {
            case S3 -> {
                if (currentS3Fragment != null && currentS3Fragment.getItem() != null) {
                    DestinationS3DTO dto = currentS3Fragment.getItem();
//                    DestinationS3DTO fresh = deepCopy(src);
//                    e.setRawConfig(null);
//                    fresh.setId(UUID.randomUUID());
                    e.setRawConfig(dto);
                    // Force DataContext to recognize the change
                    DataContext dataContext = getViewData().getDataContext();
                    dataContext.setModified(e, true);

                }
            }
            case POSTGRES -> {
                if (currentPostgresFragment != null && currentPostgresFragment.getItem() != null) {
                }
            }
            default -> {
            }
        }
    }


//    // deep copy qua EntitySerialization để có _entityName cho converter
//    @SuppressWarnings("unchecked")
//    private <T extends DestinationDTO> T deepCopy(T dto) {
//        log.info("=== deepCopy starting for: {}", dto.getClass().getSimpleName());
//        String json = entitySerialization.toJson(dto);
//        log.info("=== deepCopy JSON length: {}", json != null ? json.length() : 0);
//        T result = (T) entitySerialization.entityFromJson(json, null);
//        log.info("=== deepCopy completed: {} -> {}",
//                 System.identityHashCode(dto),
//                 result != null ? System.identityHashCode(result) : 0);
//        return result;
//    }
}
