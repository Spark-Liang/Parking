package com.park.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.ssm.service.ParkTerminalService;

@Controller
@RequestMapping("parkTerminal")
public class ParkTerminalController {
	@Autowired
	private ParkTerminalService parkTerminalService;
	
	@RequestMapping("page")
	public String page() {
		return "parkTerminal";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="park",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map park(@RequestParam("lotId")Integer lotId,@RequestParam("cardId")Long cardId) {
		Map<String, Object> result=new HashMap<>();
		
		String reason=parkTerminalService.park(lotId, cardId);
		if(reason!=null) {
			result.put("res", false);
		}else {
			result.put("res", true);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="pick",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map pick(@RequestParam("lotId")Integer lotId,@RequestParam("cardId")Long cardId) {
		Map<String, Object> result=new HashMap<>();
		
		String reason=parkTerminalService.pick(lotId, cardId);
		if(reason!=null) {
			result.put("res", false);
		}else {
			result.put("res", true);
		}
		return result;
	}
}
