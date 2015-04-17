package cn.sjzc.booksale.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractController{
	ObjectMapper mapper = new ObjectMapper();
	
	public AbstractController()
	{
		super();
	}

	protected  <T> T getCommandInfo(Object fromValue, Class<T> toValueType)
	{
		
		
		return mapper.convertValue(fromValue, toValueType);
	}
}