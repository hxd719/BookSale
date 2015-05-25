package cn.sjzc.booksale.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.sjzc.booksale.controllers.commandinfo.InformationCommandInfo;
import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.services.InformationService;
import cn.sjzc.booksale.services.UserService;
import cn.sjzc.booksale.utill.NewInfo;
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
			if(commandInfo.isSell == null||commandInfo.categoryId == null || commandInfo.content == null ) {
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
		Integer id = null;
		if(commandInfo.isSell) {
			id = service.addSellInfor(u,commandInfo);
		} else {
			id = service.addBuyInfor(u,commandInfo);
		}
		if(id > 0) {
			NewInfo.time = new Date().getTime();
		}
		return rep;
	}
	
	
	public SdkResponse update(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.isSell == null||commandInfo.id == null) {
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
			if(sellInfo.getUser().getId()!=u.getId()) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.updateSellInfor(commandInfo);
		} else {
			BuyInfor buyInfo = service.getBuyInfo(commandInfo.id);
			if(buyInfo.getUser().getId()!=u.getId()) {
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
			if(commandInfo.isSell==null||commandInfo.id == null) {
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
			if(sellInfo.getUser().getId()!=u.getId()) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.deleteSellInfor(commandInfo);
		} else {
			BuyInfor buyInfo = service.getBuyInfo(commandInfo.id);
			if(buyInfo.getUser().getId()==u.getId()) {
				rep.resultTip = "非法操作";
				return rep;
			}
			service.deleteSellInfor(commandInfo);
		}
		return rep;
	}
	
	
	public SdkResponse list(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.isSell == null) {
				rep.resultTip = "缺少重要数据";
				return rep;
			}
		} catch (Exception e) {
			rep.resultTip = "数据非法";
			return rep;
		}
		if(commandInfo.isSell) {
			List<SellInfor> listSell = service.getSellInfoList(commandInfo.categoryId, commandInfo.getPageSize(), commandInfo.getPageNum(), commandInfo.bookName);
			for (SellInfor sellInfor : listSell) {
				if(sellInfor.getBook()!=null) {
					if(sellInfor.getBook().getCover() == null) {
						sellInfor.getBook().setCover("http://123.57.219.149/Image/0000000000.jpg");
					} else {
						sellInfor.getBook().setCover("http://123.57.219.149"+sellInfor.getBook().getCover());
					}
				}
			}
			rep.responseData = listSell;
		} else {
			List<BuyInfor> listBuy = service.getBuyInfoList(commandInfo.categoryId, commandInfo.getPageSize(), commandInfo.getPageNum(), commandInfo.bookName);
			for (BuyInfor sellInfor : listBuy) {
				if(sellInfor.getBook()!=null) {
					if(sellInfor.getBook().getCover() == null) {
						sellInfor.getBook().setCover("http://123.57.219.149/Image/0000000000.jpg");
					} else {
						sellInfor.getBook().setCover("http://123.57.219.149"+sellInfor.getBook().getCover());
					}
				}
			}
			rep.responseData = listBuy;
		}
		
		return rep;
	}
	
	
	public SdkResponse personList(SdkRequest req) throws IOException	{
		SdkResponse rep = new SdkResponse();
		InformationCommandInfo commandInfo = null;
		try {
			commandInfo = getCommandInfo(req.commandInfo, InformationCommandInfo.class);
			if(commandInfo.isSell == null) {
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
			List<SellInfor> listSell = service.getSellInfoListByUserId(u.getId(), commandInfo.getPageSize(), commandInfo.getPageNum(), null);
			for (SellInfor sellInfor : listSell) {
				if(sellInfor.getBook()!=null) {
					if(sellInfor.getBook().getCover() == null) {
						sellInfor.getBook().setCover("http://123.57.219.149/Image/0000000000.jpg");
					} else {
						sellInfor.getBook().setCover("http://123.57.219.149"+sellInfor.getBook().getCover());
					}
				}
			}
			rep.responseData = listSell;
		} else {
			List<BuyInfor> listBuy = service.getBuyInfoListByUserId(u.getId(), commandInfo.getPageSize(), commandInfo.getPageNum(), null);
			for (BuyInfor sellInfor : listBuy) {
				if(sellInfor.getBook()!=null) {
					if(sellInfor.getBook().getCover() == null) {
						sellInfor.getBook().setCover("http://123.57.219.149/Image/0000000000.jpg");
					} else {
						sellInfor.getBook().setCover("http://123.57.219.149"+sellInfor.getBook().getCover());
					}
				}
			}
			rep.responseData = listBuy;
		}
		
		return rep;
	}
	
	
	

}
