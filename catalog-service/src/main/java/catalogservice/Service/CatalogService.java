package catalogservice.Service;

import catalogservice.Dto.CatalogResponse;
import catalogservice.Entity.Catalog;
import catalogservice.Repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogService {
    private final CatalogRepository catalogRepository;


    @Transactional(readOnly = true)
    @SneakyThrows   //Change for try:catch
    public List<CatalogResponse> isAvailable(List<String> UniCode) {
        log.info("Waiting...");
        Thread.sleep(10000);
        log.info("Wait ended");
        return catalogRepository.findByUniCodeIn(UniCode).stream()
                .map(catalog ->
                    CatalogResponse.builder()
                            .UniCode(catalog.getUniCode())
                            .isAvailable(catalog.getQuantity() > 0)
                            .build()

                ).toList();
    }
}
