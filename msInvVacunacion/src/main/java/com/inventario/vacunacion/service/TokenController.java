package com.inventario.vacunacion.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.vacunacion.entity.TokenEntity;
import com.inventario.vacunacion.security.JwtAuthorization;

@CrossOrigin
@RestController
@RequestMapping("/oauth2")
public class TokenController {
	
	@PostMapping("/token")
    public ResponseEntity<TokenEntity> generarToken(@RequestParam("username") String username,
    												@RequestParam("password") String password) {
		
		TokenEntity token = new TokenEntity();
		JwtAuthorization jwtToken = new JwtAuthorization();
		
		token = jwtToken.getJwtToken();
		
		return ResponseEntity.ok(token);
	}

}
