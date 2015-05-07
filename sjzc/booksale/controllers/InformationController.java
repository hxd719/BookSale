package cn.sjzc.booksale.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.services.InformationService;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;
@Controller("InformationController")
public class InformationController extends AbstractController {
	
	@Resource
	private InformationService service;
	
	
	public SdkResponse add(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		List<BuyInfor> list = service.getBuyInfoList(1, 10, 1, null);
		for (BuyInfor buyInfor : list) {
			System.out.println(buyInfor.getName()+buyInfor.getCategory().getName());
		}
		return rep;
	}
	
	/*
	public SdkResponse update(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		UserCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, UserCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if( commandInfo.password == null || commandInfo.phone == null) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = service.login(commandInfo);
		if(u != null) {
			rep.responseData = new UserInfo(u);
			return rep;
		} else {
			rep.resultTip = "用户名或密码不正确";
		}
		return rep;
	}
	
	
	public SdkResponse delete(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		UserCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, UserCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if( commandInfo.newPassword == null || commandInfo.phone == null || commandInfo.code == null) {
			rep.resultTip = "数据非法";
			return rep;
		}
		Boolean code = false;
		try {
		} catch (Exception e) {
			rep.resultTip = "服务器繁忙";
			return rep;
		}
		
		if(code) {
			User  u = service.forgetPassword(commandInfo);
			rep.responseData = new UserInfo(u);
		} else {
			rep.resultTip = "验证码错误";
			return rep;
		}
		return rep;
	}
	
	public SdkResponse search(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		UserCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, UserCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if( commandInfo.password == null || commandInfo.phone == null) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = service.updateUser(commandInfo);
		if(u != null) {
			rep.responseData = new UserInfo(u);
			return rep;
		} else {
			rep.resultTip = "原密码不正确";
		}
		return rep;
	}
	
	public SdkResponse list(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		UserCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, UserCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if( commandInfo.password == null || commandInfo.phone == null) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = service.updateUser(commandInfo);
		if(u != null) {
			rep.responseData = new UserInfo(u);
			return rep;
		} else {
			rep.resultTip = "原密码不正确";
		}
		return rep;
	}
	
	public SdkResponse getUnRead(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		UserCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, UserCommandInfo.class);
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if( commandInfo.password == null || commandInfo.phone == null) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = service.updateUser(commandInfo);
		if(u != null) {
			rep.responseData = new UserInfo(u);
			return rep;
		} else {
			rep.resultTip = "原密码不正确";
		}
		return rep;
	}
	
	
*/

}
