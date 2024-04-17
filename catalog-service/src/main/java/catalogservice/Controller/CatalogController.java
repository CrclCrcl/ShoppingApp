package catalogservice.Controller;

import catalogservice.Service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    @GetMapping("/{UniCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAvailable(@PathVariable("UniCode") String UniCode){
        return catalogService.isAvailable(UniCode);
    }
}
