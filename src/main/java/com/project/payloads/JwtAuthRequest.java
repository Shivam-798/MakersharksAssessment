package com.project.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtAuthRequest {

	private String username;//email hai username
	
	private String password;
}