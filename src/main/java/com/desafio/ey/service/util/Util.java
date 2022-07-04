package com.desafio.ey.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.desafio.ey.model.UserDTO;
import com.desafio.ey.model.UserVO;
import com.desafio.ey.service.constant.Constant;

@Service
public class Util {	
	
	public UserVO copy(UserDTO userDTO, UserVO userVO) {
		userVO.setId(userDTO.getId());
		userVO.setName(userDTO.getName());
		userVO.setEmail(userDTO.getEmail());
		userVO.setLastLogin(userDTO.getLastLogin());
		userVO.setModified(userDTO.getModified());
		userVO.setCreated(userDTO.getCreated());
		userVO.setToken(userDTO.getToken());
		userVO.setActive(userDTO.getIsActive() == 1 ? true : false);
		return userVO;
	}

	public boolean validaCorreo(String email) {
		boolean esValido = false;
		if (email != null && !email.isEmpty()) {
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			esValido = matcher.matches();
		}
		return esValido;
	}
	
	public boolean validaPass(String pass) {
		int uppercaseCounter = 0;
		int lowercaseCounter = 0;
		int digitCounter = 0;
		boolean passValida = false;
		for (int i = 0; i < pass.length(); i++) {
			char c = pass.charAt(i);
			if (Character.isUpperCase(c))
				uppercaseCounter++;
			else if (Character.isLowerCase(c))
				lowercaseCounter++;
			else if (Character.isDigit(c))
				digitCounter++;
		}if (uppercaseCounter >= Constant.MAYUS 
				&& lowercaseCounter > Constant.MINUS && digitCounter >= Constant.NUMEROS ) { 
            passValida = true;;
     }
		return passValida;
	}
}
