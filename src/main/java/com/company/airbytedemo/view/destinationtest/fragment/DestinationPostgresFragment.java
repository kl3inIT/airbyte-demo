package com.company.airbytedemo.view.destinationtest.fragment;

import com.company.airbytedemo.dto.DestinationPosgresDTO;
import com.company.airbytedemo.dto.DestinationS3DTO;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;

@FragmentDescriptor("destination-postgres-fragment.xml")
@RendererItemContainer("destinationDTODc")
public class DestinationPostgresFragment extends FragmentRenderer<VerticalLayout, DestinationPosgresDTO> {

    public DestinationPosgresDTO getItem() {
        // Lấy item từ fragment's data context
        return (DestinationPosgresDTO) getFragmentData().getContainer("destinationDTODc").getItem();
    }
}