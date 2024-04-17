package catalogservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String UniCode;
    private Integer quantity;

}
