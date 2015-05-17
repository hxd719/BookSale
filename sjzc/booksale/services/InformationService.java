package cn.sjzc.booksale.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.sjzc.booksale.controllers.commandinfo.InformationCommandInfo;
import cn.sjzc.booksale.dao.BuyInforDao;
import cn.sjzc.booksale.dao.SellInforDao;
import cn.sjzc.booksale.model.Book;
import cn.sjzc.booksale.model.BuyInfor;
import cn.sjzc.booksale.model.SellInfor;
import cn.sjzc.booksale.model.User;
import cn.sjzc.booksale.utill.PagerVO;
@Service("InformationService")
public class InformationService {
	
	@Resource
	private BuyInforDao bdao;
	
	@Resource
	private SellInforDao sdao;


	@Resource
	private BookService bookService;
	
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private MessageService messageService;
	
	
	
	public PagerVO getBuyInfoList( Integer pageSize,Integer pageNum,String searchKey) {
		PagerVO data = null;
		String sql ="select b from BuyInfor b where  b.bookName like ? order by b.publishTime desc";
		data = bdao.findPaginated(sql,"%"+searchKey+"%" ,(pageNum-1)*pageSize, pageSize);
		return data;
	}
	
	
	public PagerVO getSellInfoList( Integer pageSize,Integer pageNum,String searchKey) {
		PagerVO data = null;
		String sql ="select b from SellInfor b where  b.bookName like ? order by b.publishTime desc";
		data = bdao.findPaginated(sql,"%"+searchKey+"%" ,(pageNum-1)*pageSize, pageSize);
		return data;
	}
	

	@SuppressWarnings("unchecked")
	public List<BuyInfor> getBuyInfoList(Integer categoryId,Integer pageSize,Integer pageNum,String searchKey) {
		List<BuyInfor> list = null;
		PagerVO data = null;
		if(searchKey != null) {
			String sql ="select b from BuyInfor b , Category c  where  b.deadline > now() and c.id=? and b.name like ? order by b.publishTime desc";
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
			list = (List<SellInfor>)(sdao.findPaginated(sql,new Object[]{categoryId,"%"+searchKey+"%"}, (pageNum-1)*pageSize, pageSize).getDatas());
		} else {
			String sql ="select b from SellInfor b, Category c  where  b.deadline > now() and c.id=? order by b.publishTime desc";
			list = (List<SellInfor>)(sdao.findPaginated(sql,categoryId ,(pageNum-1)*pageSize, pageSize).getDatas());
		}
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private List<BuyInfor> findBuyInfoList(Integer bookId,String searchKey) {
		List<BuyInfor> list = null;
		PagerVO data = null;
		if(bookId != null) {
			String sql ="select s from BuyInfor s,Book b where  s.deadline > now() and  b.id = ? order by s.publishTime desc";
			data = bdao.findPaginated(sql,bookId);
		} else {
			String sql ="select s from BuyInfor s  where  s.deadline > now() and  s.bookName like ? order by s.publishTime desc";
			data = bdao.findPaginated(sql,"%"+searchKey+"%");
		}
		list = (List<BuyInfor>)data.getDatas();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BuyInfor> getBuyInfoListByUserId(Integer userId,Integer pageSize,Integer pageNum,String searchKey) {
		List<BuyInfor> list = null;
		PagerVO data = null;
		if(searchKey != null) {
			String sql ="select b from BuyInfor b , User u  where  u.id=? and b.name like ? order by b.publishTime desc";
			data = bdao.findPaginated(sql,new Object[]{userId,"%"+searchKey+"%"} ,(pageNum-1)*pageSize, pageSize);
		} else {
			String sql ="select b from BuyInfor b , User u  where u.id = ? order by b.publishTime desc";
			data = bdao.findPaginated(sql,userId, (pageNum-1)*pageSize, pageSize);
			list = (List<BuyInfor>) data.getDatas();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SellInfor> getSellInfoListByUserId(Integer userId,Integer pageSize,Integer pageNum,String searchKey) {
		List<SellInfor> list = null;
		if(searchKey != null) {
			String sql ="select b from SellInfor b, User u   where  u.id=? and b.bookName like ? order by b.publishTime desc";
			list = (List<SellInfor>)(sdao.findPaginated(sql,new Object[]{userId,"%"+searchKey+"%"}, (pageNum-1)*pageSize, pageSize).getDatas());
		} else {
			String sql ="select b from SellInfor b, User u   where  u.id=? order by b.publishTime desc";
			list = (List<SellInfor>)(sdao.findPaginated(sql,userId ,(pageNum-1)*pageSize, pageSize).getDatas());
		}
		return list;
	}
	
	public Integer addBuyInfor(User u, InformationCommandInfo commandinfo) {
		BuyInfor info = new BuyInfor();
		Date time = new Date();
		info.setBookName(commandinfo.bookName);
		info.setCategory(categoryService.getCategoryById(commandinfo.categoryId));
		info.setContent(commandinfo.content);
		info.setPublishTime(time);
		info.setUser(u);
		info.setDeadline(new Date(time.getTime()+1296000000));
		
		if(commandinfo.bookId != null) {
			Book b = bookService.getBookById(commandinfo.bookId);
			info.setBook(b);
			info.setBookName(b.getName());
		}
		bdao.save(info);
	
		
		return info.getId();
	}
	
	
	public Integer addSellInfor(User u,InformationCommandInfo commandinfo) {
		SellInfor info = new SellInfor();
		Date time = new Date();
		info.setBookName(commandinfo.bookName);
		if(commandinfo.bookId != null) {
			Book b = bookService.getBookById(commandinfo.bookId);
			info.setBook(b);
			info.setBookName(b.getName());
		}
		info.setCategory(categoryService.getCategoryById(commandinfo.categoryId));
		info.setPublishTime(time);
		info.setDeadline(new Date(time.getTime()+1296000000));
		info.setPrice(commandinfo.price);
		info.setUser(u);
		info.setDescription(commandinfo.content);
		sdao.save(info);
		
		if(commandinfo.bookId != null||(commandinfo.bookName != null &&!commandinfo.bookName.equals(""))) {
			List<BuyInfor> list = findBuyInfoList(commandinfo.bookId, commandinfo.bookName);
			for (BuyInfor sellInfor : list) {
				messageService.addNewMessage(sellInfor.getUser().getId(), info);
			}
		}
		
		return info.getId();
	}
	
	public BuyInfor getBuyInfo(Integer id) {
		return bdao.findById(BuyInfor.class, id);
	}
	
	public SellInfor getSellInfor(Integer id) {
		return sdao.findById(SellInfor.class, id);
	}
	
	public int updateBuyInfor(InformationCommandInfo commandinfo) {
		BuyInfor info = bdao.findById(BuyInfor.class, commandinfo.id);
		Date time = new Date();
		if(commandinfo.bookName != null) {
			info.setBookName(commandinfo.bookName);
		}
		if(commandinfo.categoryId != null) {
			info.setCategory(categoryService.getCategoryById(commandinfo.categoryId));
		}
		
		if(commandinfo.content != null) {
			info.setContent(commandinfo.content);
		}
		info.setDeadline(new Date(time.getTime()+1296000000));
		if(commandinfo.bookId != null) {
			Book b = bookService.getBookById(commandinfo.bookId);
			info.setBook(b);
			info.setBookName(b.getName());
		}
		
		bdao.update(info);
		return info.getId();
	}
	
	public int updateSellInfor( InformationCommandInfo commandinfo) {
		SellInfor info = sdao.findById(SellInfor.class, commandinfo.id);
		Date time = new Date();
		if(commandinfo.bookName != null) {
			info.setBookName(commandinfo.bookName);
		}
		if(commandinfo.bookId != null) {
			Book b = bookService.getBookById(commandinfo.bookId);
			info.setBook(b);
			info.setBookName(b.getName());
		}
		if(commandinfo.categoryId != null) {
			info.setCategory(categoryService.getCategoryById(commandinfo.categoryId));
		}
		
		if(commandinfo.price != null) {
			info.setPrice(commandinfo.price);
		}
		if(commandinfo.content != null) {
			info.setDescription(commandinfo.content);
		}
		info.setDeadline(new Date(time.getTime()+1296000000));
		sdao.update(info);
		return info.getId();
	}
	
	
	public void deleteBuyInfor(InformationCommandInfo commandinfo) {
		BuyInfor info = new BuyInfor();
		info.setId(commandinfo.id);
		bdao.del(info);
	}
	
	public void deleteBuyInfor(Integer id) {
		BuyInfor info = new BuyInfor();
		info.setId(id);
		bdao.del(info);
	}
	
	public void deleteSellInfor(InformationCommandInfo commandinfo) {
		SellInfor info = new SellInfor();
		info.setId(commandinfo.id);
		sdao.del(info);
	}
	
	public void deleteSellInfor(Integer id) {
		SellInfor info = new SellInfor();
		info.setId(id);
		sdao.del(info);
	}


}
