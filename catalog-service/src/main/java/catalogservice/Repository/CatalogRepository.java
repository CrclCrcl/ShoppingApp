package catalogservice.Repository;

import catalogservice.Entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {



    List<Catalog> findbyUniCodeIn(List<String> uniCode);
}
