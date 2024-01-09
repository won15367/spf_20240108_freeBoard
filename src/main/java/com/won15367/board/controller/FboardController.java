package com.won15367.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.won15367.board.command.FBWriteCommand;
import com.won15367.board.command.FbContentViewCommand;
import com.won15367.board.command.FblistCommand;
import com.won15367.board.dto.FboardDto;


@Controller
public class FboardController {
	
	DataSource datasource;
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request) {
				
		String fbtitle = request.getParameter("fbtitle");
		String fbname = request.getParameter("fbname");
		String fbcontent = request.getParameter("fbcontent");
		
		FBWriteCommand command = new FBWriteCommand();
		command.execute(fbtitle, fbname, fbcontent);
			
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		FblistCommand command = new FblistCommand();
		ArrayList<FboardDto> dtos = command.execute();
		
		model.addAttribute("fboardDtos", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		String fbnum = request.getParameter("fbnum");
		
		FbContentViewCommand command = new FbContentViewCommand();
		FboardDto dto = command.execute(fbnum);
		
		model.addAttribute("fboardDto", dto);
		
		return "content_view";
	}

}
