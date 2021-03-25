/*
 * package com.webAPI.training.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.webAPI.training.JWT.JwtAuthenticationFilter; import
 * com.webAPI.training.JWT.JwtTokenCreator;
 * 
 * 
 * @RestController public class AdminController {
 * 
 * @Autowired private JwtTokenCreator tokenProvider;
 * 
 * @Autowired private Authentication authenticationManager;
 * 
 * @PostMapping("/authenticate") public String adminpage() { try {
 * authenticationManager. } return tokenProvider.addAuthentication(res,
 * username);
 * 
 * } }
 */