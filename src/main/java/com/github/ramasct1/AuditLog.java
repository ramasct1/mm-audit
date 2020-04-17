package com.github.ramasct1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuditLog {

    private Object oldValue;

    private Object newValue;

    private Integer commitVersion;

    private String action;

    private String entityName;

    private String entityId;

    private String tenantId;

    private String locationId;

    private String comments;

    private Object meta;
}
