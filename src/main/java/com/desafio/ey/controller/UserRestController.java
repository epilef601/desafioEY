package com.desafio.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.ey.model.DataUserDTO;
import com.desafio.ey.model.ResultVO;
import com.desafio.ey.model.UserDTO;
import com.desafio.ey.model.UserVO;
import com.desafio.ey.service.IUserService;

@RestController
public class UserRestController {
	@Autowired
	private IUserService userService;

	@GetMapping("/usuario/getUsers")
	public List<UserDTO> list() {
		return userService.list();
	}

	@GetMapping("/usuario/verifica/{email}")
	public ResultVO get(@PathVariable String email) {
		return userService.verificaUsuario(email);
	}

	@PostMapping("/usuario/registro")
	public UserVO crearUsuario(@RequestBody DataUserDTO dataUser) {
		return userService.regitrarUsuario(dataUser);
	}

}
