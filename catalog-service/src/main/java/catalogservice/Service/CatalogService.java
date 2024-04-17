package catalogservice.Service;

import catalogservice.Repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;


    @Transactional(readOnly = true)
    public boolean isAvailable(String UniCode){
        return catalogRepository.findbyUniCode().isPresent();

    }
}
