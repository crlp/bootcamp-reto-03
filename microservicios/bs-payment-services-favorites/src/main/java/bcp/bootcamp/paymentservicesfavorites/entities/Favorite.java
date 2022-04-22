package bcp.bootcamp.paymentservicesfavorites.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    private String id;
    private String user;
    private String codeService;
    private String name;
    private String type;
    private String channel;
}
