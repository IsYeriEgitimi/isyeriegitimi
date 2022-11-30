package com.fu.isyeri.errors;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private String message = "Kullanıcı adı veya şifre hatalı.";

}
