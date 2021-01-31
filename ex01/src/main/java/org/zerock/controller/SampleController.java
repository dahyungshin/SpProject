package org.zerock.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	@RequestMapping(value="" , method = {RequestMethod.GET , RequestMethod.POST})
	public void basic() {
		
		log.info("basic....................................");
		
	
	}
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		
		log.info("basic get only get ...........................");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info(""+dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex01(@RequestParam("name") String name , @RequestParam("age") int age) {
		
		log.info("name : "+ name);
		log.info("age : "+age);
		return "ex02";
	}
	@GetMapping("/ex02List") // 여러개의 매개변수가 올시 리스트 ArrayList<String> 생성 됨 
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array") //배열
	public String ex02Array(@RequestParam("ids")String[] ids) {
		
		log.info("ids : " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	/**
	 * SampleDTOList 데이터를 SampleDTO 에 넣어서 가져오기 
	 * **/
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) { 
		 
		log.info("list dtos : " + list);
		return "ex02Bean";
	}

	/******
	 * Date 변환 
	 * ****/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(dateFormat,false));
	}
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		System.out.println(todo);
		log.info("todo : "+todo);
		return "ex03"; 
	}
	/*
	 *Model, modelAttribute 
	 * */
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto,@ModelAttribute("page") int page) {
		
		log.info("dto : "+ dto);
		log.info("page : "+ page);
		return "sample/ex04";
	}
	
	/**
	 * 리턴타입 
	 */
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05..............");
	}
	

	
} 

 