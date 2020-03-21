package proxy;
import invoke.MyInvokeHandler;
import java.lang.reflect.Method;
import dao.MyDao;

public class $WenHaoProxy implements MyDao{

	private MyInvokeHandler h;

	public $WenHaoProxy(MyInvokeHandler h) {
		this.h = h;
	}

	public void update(Long p0){
		Class clazz = MyDao.class;
		Class[] argTypes = new Class[]{Long.class};
		Object[] argValues = new Object[]{p0};
		Object res = null;
		try{
			Method method = clazz.getDeclaredMethod("update", argTypes);
			this.h.invoke(this, method, argValues);
		} catch(Throwable e){
			e.printStackTrace();
		}
	}
	public String getBy(Long p0, String p1){
		Class clazz = MyDao.class;
		Class[] argTypes = new Class[]{Long.class, String.class};
		Object[] argValues = new Object[]{p0, p1};
		Object res = null;
		try{
			Method method = clazz.getDeclaredMethod("getBy", argTypes);
			res = this.h.invoke(this, method, argValues);
		} catch(Throwable e){
			e.printStackTrace();
		}
		return (String)res;
	}
	public void deleteAll(){
		Class clazz = MyDao.class;
		Class[] argTypes = new Class[]{};
		Object[] argValues = new Object[]{};
		Object res = null;
		try{
			Method method = clazz.getDeclaredMethod("deleteAll", argTypes);
			this.h.invoke(this, method, argValues);
		} catch(Throwable e){
			e.printStackTrace();
		}
	}
}
