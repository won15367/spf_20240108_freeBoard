package com.won15367.board.command;

import com.won15367.board.dao.FreeBoardDao;

public class FBWriteCommand {
	
	public void execute(String fbtitle, String fbname, String fbcontent) {
		
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		freeBoardDao.write(fbtitle, fbname, fbcontent);
	}

}
