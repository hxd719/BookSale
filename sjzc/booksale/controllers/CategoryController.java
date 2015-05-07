package cn.sjzc.booksale.controllers;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.services.CategoryService;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;

@Controller("CategoryController")
public class CategoryController extends AbstractController {
	@Resource
	private CategoryService service;
	
	public SdkResponse list(SdkRequest req) throws IOException {
		SdkResponse rep = new SdkResponse();
		rep.responseData = service.getCategorise();
		return rep;
	}

}
