package com.won15367.board.command;

import com.won15367.board.dao.FreeBoardDao;
import com.won15367.board.dto.FboardDto;

public class FbContentViewCommand {
	
	public FboardDto execute(String req_fbnum) {
		
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		FboardDto dto = freeBoardDao.content_view(req_fbnum);
		
		return dto;
	}

}
