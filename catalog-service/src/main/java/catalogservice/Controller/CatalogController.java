package catalogservice.Controller;

import catalogservice.Dto.CatalogResponse;
import catalogservice.Service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogResponse> isAvailable(@RequestParam List<String> UniCode){
        return catalogService.isAvailable(UniCode);
    }
}
