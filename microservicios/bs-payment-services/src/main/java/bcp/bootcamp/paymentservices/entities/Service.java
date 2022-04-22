package bcp.bootcamp.paymentservices.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table("tb_service")
public class Service {

    @Id
    private Integer id;
    private String code;
    private String name;
    private String channel;
}
