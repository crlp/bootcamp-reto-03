package bcp.bootcamp.paymentservices.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Table("tb_operation")
public class Operation {
    @Id
    private Integer id;
    private String code;
    private String supply;
    private Double amount;
    private int favorite;
    private LocalDateTime date;
    private String channel;
}
