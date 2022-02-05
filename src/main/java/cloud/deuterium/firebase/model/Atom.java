package cloud.deuterium.firebase.model;

import com.google.cloud.Timestamp;
import lombok.*;

/**
 * Created by Milan Stojkovic 01-Oct-2021
 */

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atom {
    private String id;
    private int atomNumber;
    private double mass;
    private String name;
    private String symbol;
    private Timestamp created;
}
