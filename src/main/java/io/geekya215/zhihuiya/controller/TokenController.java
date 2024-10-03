package io.geekya215.zhihuiya.controller;

import io.geekya215.zhihuiya.request.TokenRequest;
import io.geekya215.zhihuiya.service.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@CrossOrigin
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public Object getToken(@RequestBody TokenRequest tokenRequest) {
        return tokenService.getToken(tokenRequest);
    }
}
