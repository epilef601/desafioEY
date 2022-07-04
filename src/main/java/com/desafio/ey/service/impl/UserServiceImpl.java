package com.desafio.ey.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.ey.model.DataUserDTO;
import com.desafio.ey.model.ResultVO;
import com.desafio.ey.model.UserDTO;
import com.desafio.ey.model.UserVO;
import com.desafio.ey.persistence.UserRepository;
import com.desafio.ey.service.IUserService;
import com.desafio.ey.service.constant.Constant;
import com.desafio.ey.service.util.Util;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Util util;

	@Override
	public List<UserDTO> list() {
		return (List<UserDTO>) userRepository.findAll();
	}

	public ResultVO verificaUsuario(String email) {
		ResultVO result = new ResultVO();
		UserDTO user = userRepository.findByEmail(email).orElse(null);
		if (user != null) {
			result.setCode(Constant.USER_EXIST_CODE);
			result.setMessage(Constant.USER_EXIST_MSG + " Email: " + user.getEmail());
		} else {
			result.setCode(Constant.USER_NO_EXIST_CODE);
			result.setMessage(Constant.USER_NO_EXIST_MSG);
		}
		return result;
	}

	public UserVO regitrarUsuario(DataUserDTO dataUsuario) {
		Date date = new Date();
		UserVO respuesta = new UserVO();
		ResultVO result = new ResultVO();
		try {
			ResultVO usuarioExiste = verificaUsuario(dataUsuario.getEmail());
			if (usuarioExiste.getCode().equalsIgnoreCase("98")) {
				result.setCode(Constant.USER_EXIST_CODE);
				result.setMessage(Constant.USER_EXIST_MSG);
				respuesta.setResult(result);
			} else if (util.validaCorreo(dataUsuario.getEmail())) {
				if (util.validaPass(dataUsuario.getPassword())) {
					UserDTO user = new UserDTO();
					final String uuidToken = UUID.randomUUID().toString().replace("-", "");
					user.setName(dataUsuario.getName());
					user.setEmail(dataUsuario.getEmail());
					user.setPassword(dataUsuario.getPassword());
					user.setCreated(date);
					user.setModified(date);
					user.setLastLogin(date);
					user.setToken(uuidToken);
					user.setActive(1);
					user = userRepository.save(user);
					if (user != null) {
						result.setCode("00");
						result.setMessage("Usuario creado con exito.");
						util.copy(user, respuesta);
					} else {
						result.setCode("99");
						result.setMessage("Error al crear usuario.");
					}
				} else {
					result.setCode("96");
					result.setMessage("Contraseña poco segura.");
				}
			} else {
				result.setCode("97");
				result.setMessage("Correo no valido.");
			}
		} catch (Exception e) {
			result.setCode(Constant.EXCEPTION_CODE);
			result.setMessage(Constant.EXCEPTION_MSG);
		}
		respuesta.setResult(result);
		return respuesta;
	}

}
