package cn.sjzc.booksale.utill;


public class SystemContext {
	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	
	
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
	
	public static void setOffset(int _offset){
		offset.set(_offset);
	}
	
	public static int getOffset(){
		Integer _offset = (Integer)offset.get();
		if(_offset == null){
			return 0;
		}
		return _offset;
	}
	
	public static void removeOffset(){
		offset.remove();
	}
	
	public static void setPagesize(int _pagesize){
		pagesize.set(_pagesize);
	}
	
	public static int getPagesize(){
		Integer _pagesize = (Integer)pagesize.get();
		if(_pagesize == null){
			return Integer.MAX_VALUE;
		}
		return _pagesize;
	}
	
	public static void removePagesize(){
		pagesize.remove();
	}
	
}
