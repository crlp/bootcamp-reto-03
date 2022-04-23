package bcp.bootcamp.paymentservicesfavorites.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document("favorite")
public class Favorite {
    private String id;
    private String user;
    private String codeService;
    private String name;
    private String type;
    private String channel;
}
