package catalogservice.Service;

import catalogservice.Dto.CatalogResponse;
import catalogservice.Entity.Catalog;
import catalogservice.Repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;


    @Transactional(readOnly = true)
    public List<CatalogResponse> isAvailable(List<String> UniCode) {
        return catalogRepository.findbyUniCodeIn(UniCode).stream()
                .map(catalog ->
                    CatalogResponse.builder()
                            .UniCode(catalog.getUniCode())
                            .isAvailable(catalog.getQuantity() > 0)
                            .build()

                ).toList();
    }
}
