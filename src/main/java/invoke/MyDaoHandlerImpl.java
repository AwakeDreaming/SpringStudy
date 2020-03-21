package invoke;

import dao.MyDao;
import java.lang.reflect.Method;

/**
 * @author gaowenhao
 * @date 2020-03-21 18:34
 */
public class MyDaoHandlerImpl implements MyInvokeHandler {

    private MyDao myDao;

    public MyDaoHandlerImpl(MyDao mydao){
        this.myDao = mydao;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do pre proxy logic");
        String res = (String)method.invoke(myDao, args);
        System.out.println("do post proxy logic");
        return res;
    }

}
