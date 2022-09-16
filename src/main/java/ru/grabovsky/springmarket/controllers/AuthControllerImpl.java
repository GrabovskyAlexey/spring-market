package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.AuthRequestDto;
import ru.grabovsky.market.api.dto.AuthResponseDto;
import ru.grabovsky.springmarket.controllers.interfaces.AuthController;
import ru.grabovsky.springmarket.services.interfaces.UserService;

/**
 * Имплементация AuthController
 *
 * @author grabovsky.alexey
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthResponseDto> authenticate(AuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(
                new AuthResponseDto(
                        userService.getTokenByUsername(request.getUsername())
                )
        );
    }
}
