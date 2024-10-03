package io.geekya215.zhihuiya.controller;

import io.geekya215.zhihuiya.request.QuerySearchRequest;
import io.geekya215.zhihuiya.service.SearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public Object queryPatent(
            @RequestHeader("Authorization") String authorization,
            @RequestBody QuerySearchRequest request,
            @RequestParam String apikey
    ) {
        String token = authorization.split(" ")[1];
        return searchService.getSimpleBibliography(request, apikey, token);
    }
}
