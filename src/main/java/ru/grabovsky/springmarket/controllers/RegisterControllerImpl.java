package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.AuthResponseDto;
import ru.grabovsky.market.api.dto.RegisterRequestDto;
import ru.grabovsky.springmarket.controllers.interfaces.RegisterController;
import ru.grabovsky.springmarket.entity.User;
import ru.grabovsky.springmarket.services.interfaces.RegisterService;
import ru.grabovsky.springmarket.services.interfaces.UserService;

import javax.validation.Valid;

/**
 * Имплементация RegisterController
 *
 * @author grabovsky.alexey
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterControllerImpl implements RegisterController {
    private final RegisterService registerService;
    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        User user = registerService.register(request);
        String token = userService.getTokenByUsername(user.getUsername());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
