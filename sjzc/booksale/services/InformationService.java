package cn.sjzc.booksale.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.dao.BuyInforDao;
import cn.sjzc.booksale.dao.SellInforDao;
import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.utill.PagerVO;
@Service("InformationService")
public class InformationService {
	
	@Resource
	private BuyInforDao bdao;
	
	@Resource
	private SellInforDao sdao;



	@SuppressWarnings("unchecked")
	public List<BuyInfor> getBuyInfoList(Integer categoryId,Integer pageSize,Integer pageNum,String searchKey) {
		List<BuyInfor> list = null;
		PagerVO data = null;
		if(searchKey != null) {
			String sql ="select b from BuyInfo b , Category c  where  b.deadline > now() and c.id=? and b.name like ? order by b.publishTime desc";
			data = bdao.findPaginated(sql,new Object[]{categoryId,"%"+searchKey+"%"} ,(pageNum-1)*pageSize, pageSize);
		} else {
			String sql ="select b from BuyInfor b , Category c where  b.deadline > now() and c.id = ? order by b.publishTime desc";
			data = bdao.findPaginated(sql,categoryId, (pageNum-1)*pageSize, pageSize);
			list = (List<BuyInfor>) data.getDatas();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SellInfor> getSellInfoList(Integer categoryId,Integer pageSize,Integer pageNum,String searchKey) {
		List<SellInfor> list = null;
		if(searchKey != null) {
			String sql ="select b from SellInfor b, Category c  where  b.deadline > now() and c.id=? and b.bookName like ? order by b.publishTime desc";
			list = (List<SellInfor>)sdao.findPaginated(sql,new Object[]{categoryId,"%"+searchKey+"%"}, (pageNum-1)*pageSize, pageSize);
		} else {
			String sql ="select b from SellInfor b, Category c  where  b.deadline > now() and c.id=? order by b.publishTime desc";
			list = (List<SellInfor>)sdao.findPaginated(sql,categoryId ,(pageNum-1)*pageSize, pageSize);
		}
		return list;
	}


}
