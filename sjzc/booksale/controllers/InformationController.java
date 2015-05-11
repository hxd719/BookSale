package cn.sjzc.booksale.controllers;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.controllers.commandinfo.InformationCommandInfo;
import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.services.InformationService;
import cn.sjzc.booksale.services.UserService;
import cn.sjzc.booksale.utill.SdkRequest;
import cn.sjzc.booksale.utill.SdkResponse;
@Controller("InformationController")
public class InformationController extends AbstractController {
	
	@Resource
	private InformationService service;
	
	
	@Resource
	private UserService userService;
	
	
	public SdkResponse add(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.categoryId == null || commandInfo.content == null ) {
				rep.resultTip = "缺少重要数据";
				return rep;
			}
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		
		User u = userService.getUserByToken(req.token);
		if(u == null) {
			rep.resultTip = "请登录";
			return rep;
		}
		
		if(commandInfo.isSell) {
			service.addSellInfor(u,commandInfo);
		} else {
			service.addBuyInfor(u,commandInfo);
		}
		return rep;
	}
	
	
	public SdkResponse update(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.id == null) {
				rep.resultTip = "缺少重要数据";
				return rep;
			}
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = userService.getUserByToken(req.token);
		if(u == null) {
			rep.resultTip = "请登录";
			return rep;
		}
		if(commandInfo.isSell){
			SellInfor sellInfo = service.getSellInfor(commandInfo.id);
			if(!sellInfo.getUser().getId().equals(u.getId())) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.updateSellInfor(commandInfo);
		} else {
			BuyInfor buyInfo = service.getBuyInfo(commandInfo.id);
			if(!buyInfo.getUser().getId().equals(u.getId())) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.updateBuyInfor(commandInfo);
		}
		return rep;
	}
	
	
	public SdkResponse delete(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.id == null) {
				rep.resultTip = "缺少重要数据";
				return rep;
			}
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		User u = userService.getUserByToken(req.token);
		if(u == null) {
			rep.resultTip = "请登录";
			return rep;
		}
		if(commandInfo.isSell){
			SellInfor sellInfo = service.getSellInfor(commandInfo.id);
			if(!sellInfo.getUser().getId().equals(u.getId())) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.deleteSellInfor(commandInfo);
		} else {
			BuyInfor buyInfo = service.getBuyInfo(commandInfo.id);
			if(!buyInfo.getUser().getId().equals(u.getId())) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.deleteSellInfor(commandInfo);
		}
		return rep;
	}
	
	/*
	public SdkResponse search(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
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
