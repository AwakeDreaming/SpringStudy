import dao.MyDaoImpl;
import invoke.MyDaoHandlerImpl;
import invoke.MyInvokeHandler;
import proxy.$WenHaoProxy;

/**
 * @author gaowenhao
 * @date 2020-03-21 17:55
 */
public class Test {

    public static void main(String args[]) throws Exception{
        MyInvokeHandler handler = new MyDaoHandlerImpl(new MyDaoImpl());
//        MyDao proxyDao = null;
//        try{
//            proxyDao = (MyDao) MyProxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{MyDao.class}, handler);
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//        System.out.println(proxyDao.getBy(20457892L, "music"));
        $WenHaoProxy wenHaoProxy = new $WenHaoProxy(handler);
        wenHaoProxy.getBy(24923423L, "music");
        wenHaoProxy.update(24923423L);
        wenHaoProxy.deleteAll();
    }

}
