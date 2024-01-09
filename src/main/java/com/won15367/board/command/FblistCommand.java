package com.won15367.board.command;

import java.util.ArrayList;

import com.won15367.board.dao.FreeBoardDao;
import com.won15367.board.dto.FboardDto;

public class FblistCommand {
	
	public ArrayList<FboardDto> execute() {
		
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		ArrayList<FboardDto> dtos = freeBoardDao.list();
		
		return dtos;
	}
	

}
