package bcp.bootcamp.uxpaymentservice.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    private Integer id;
    private String code;
    private String name;
    private String channel;
}
