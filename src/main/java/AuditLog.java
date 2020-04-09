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

    private String eventType;

    private String collectionName;

    private String entityId;

    private String tenantId;
}
