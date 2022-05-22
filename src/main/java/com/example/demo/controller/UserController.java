package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserRequest;
import com.example.demo.entity.UserResponse;
import com.example.demo.jwt.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
//@CrossOrigin(origins = "*")
@Api(value="This controller class gives Jwt Token")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JWTUtil jutil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/login")
	@ApiOperation(value="This method takes in the User Request Model, authenticates the data and Returns the Jwt Token.")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest urequest) {
		logger.info("Initiating Authentication");
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(urequest.getUsername(), urequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.info("Successfully Authenticated... Generating Jwt Token...");

		String token = jutil.generateToken(authentication);
		logger.info("Token is Generated  Successfully... Sending The token to Client..");

		UserResponse ureponse = new UserResponse(token);
		return new ResponseEntity<>(ureponse, HttpStatus.OK);
	}
}
