package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;
import com.dev.torhugo.hub_payments.lib.data.dto.LinkResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.store.StoreResponseDTO;
import org.springframework.stereotype.Component;

import static com.dev.torhugo.hub_payments.util.GenericUtil.genericHost;

@Component
public class StoreMapper {
    public StoreModel mapperToModel(final StoreRequestDTO store) {
        return StoreModel.builder()
                .cpfOrCnpj(store.cpfOrCnpj())
                .email(store.email())
                .password(store.password())
                .build();
    }

    public StoreResponseDTO mapperToResponse(final StoreModel model) {
        return StoreResponseDTO.builder()
                .storeId(model.getStoreId())
                .email(model.getEmail())
                .cpfOrCnpj(model.getCpfOrCnpj())
                .link(LinkResponseDTO.builder()
                        .method("[GET]")
                        .href("")
                        .build())
                .build();
    }

    private String buildHref(final Long storeId){
        return genericHost("", "");
    }
}
