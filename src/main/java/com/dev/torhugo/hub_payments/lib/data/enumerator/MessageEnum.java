package com.dev.torhugo.hub_payments.lib.data.enumerator;

import lombok.Getter;

@Getter
public enum MessageEnum {
    CONTACT_SUPPORT("Get in touch with support."),
    NOT_FOUND("Entity not found!."),
    FOUND_RECURRENCE("Entity already exists!."),
    NOT_APPLICABLE("Not Applicable!.");

    private final String description;


    MessageEnum(final String description) {
        this.description = description;
    }

    public String getDescription(final String entity, final String object){
        return description.concat(" | Entity: ").concat(getMessage(entity, object));
    }

    private String getMessage(final String entity, final String object){
        return entity.concat(" | Identifier: ").concat(object);
    }
}
