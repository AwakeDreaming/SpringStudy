package invoke;

import java.lang.reflect.Method;

/**
 * @author gaowenhao
 * @date 2020-03-21 13:33
 */
public interface MyInvokeHandler {

    /**
     * 代理方法, 将目标方法的逻辑增强, 内部调用目标对象的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}
