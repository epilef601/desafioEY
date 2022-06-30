package com.desafio.ey.service;

import java.util.List;

import com.desafio.ey.model.DataUserDTO;
import com.desafio.ey.model.ResultVO;
import com.desafio.ey.model.UserDTO;
import com.desafio.ey.model.UserVO;

public interface IUserService {
	
	public List<UserDTO> list();
	
	public ResultVO verificaUsuario(String email);

	public UserVO regitrarUsuario(DataUserDTO dataUser);

}
