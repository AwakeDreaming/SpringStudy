package proxy;

import invoke.MyInvokeHandler;
import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 自己实现一个代理类, 尝试代理任意逻辑
 * @author gaowenhao
 * @date 2020-03-21 13:21
 */
public class MyProxy {

    private static String LINE = "\n";
    private static String SEM = ";";
    private static String CLASS_NAME = "$WenHaoProxy";
    private static String COMMA = ",";
    private static String TAB = "\t";

    public static Object newProxyInstance(ClassLoader loader, Class[] interfaces, MyInvokeHandler handler) throws Throwable{
        if(interfaces == null || interfaces.length == 0 || loader == null || handler == null){
            throw new Exception("illegal argument");
        }
        String sourceCode = generateSourceCode(interfaces, handler);
        PrintWriter writer = new PrintWriter(new File("/Users/awakedreaming/Documents/study/SpringStudy/src/main/java/proxy/$WenHaoProxy.java"));
        writer.println(sourceCode);
        writer.flush();
        writer.close();
        //TODO:动态编译
        //TODO:load进内存
        //TODO:利用构造函数反射出对象
        return null;
    }

    /**
     * 生成代理类源代码
     * @param interfaces
     * @param handler
     * @return
     */
    private static String generateSourceCode(Class[] interfaces, MyInvokeHandler handler){
        String packCode = "package proxy" + SEM + LINE;
        //import
        String importSource = "";
        importSource += "import " + MyInvokeHandler.class.getName() + SEM + LINE;
        importSource += "import java.lang.reflect.Method" + SEM + LINE;
        for(Class clazz : interfaces){
            importSource += "import " + clazz.getName() + SEM + LINE;
        }
        importSource += LINE;
        //类定义
        String classDef = "public class " + CLASS_NAME + " implements ";
        classDef += interfaces[0].getSimpleName();
        for(int i = 1; i< interfaces.length; i++){
            classDef += COMMA + " " + interfaces[i].getSimpleName();
        }
        classDef += "{" + LINE;
        classDef += LINE;
        //属性声明
        String propsDef = TAB + "private MyInvokeHandler h" + SEM + LINE;
        propsDef += LINE;
        //构造器声明
        String constructor = TAB + "public " + CLASS_NAME + "(MyInvokeHandler h) {" + LINE;
        constructor += TAB + TAB + "this.h = h" + SEM + LINE;
        constructor += TAB + "}" + LINE;
        constructor += LINE;
        //代理所有接口的所有方法
        String methodInvokes = "";
        for(Class clazz : interfaces){
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                Class returnType = method.getReturnType();
                Class[] argTypes = method.getParameterTypes();
                String fullArgs = "";
                String typeArgs = "";
                String valueArgs = "";
                if(argTypes.length > 0){
                    fullArgs = argTypes[0].getSimpleName() + " p0";
                    typeArgs = argTypes[0].getSimpleName() + ".class";
                    valueArgs = "p0";
                    for(int i = 1; i < argTypes.length; i++){
                        fullArgs += COMMA + " " + argTypes[i].getSimpleName() + " p" + i;
                        typeArgs += COMMA + " " + argTypes[i].getSimpleName() + ".class";
                        valueArgs += COMMA + " p" + i;
                    }
                }
                String tmpInvoke = "";
                tmpInvoke += TAB + "public " + returnType.getSimpleName() + " " + method.getName() + "(" + fullArgs + "){" + LINE;
                tmpInvoke += TAB + TAB + "Class clazz = " + clazz.getSimpleName() + ".class" + SEM + LINE;
                tmpInvoke += TAB + TAB + "Class[] argTypes = new Class[]{" + typeArgs + "}" + SEM + LINE;
                tmpInvoke += TAB + TAB + "Object[] argValues = new Object[]{" + valueArgs + "}" + SEM + LINE;
                tmpInvoke += TAB + TAB + "Object res = null" + SEM + LINE;
                tmpInvoke += TAB + TAB +"try{" + LINE;
                tmpInvoke += TAB + TAB + TAB + "Method method = clazz.getDeclaredMethod(\"" + method.getName() + "\"" + COMMA + " argTypes)" + SEM + LINE;
                if(!returnType.getSimpleName().equalsIgnoreCase("void")){
                    tmpInvoke += TAB + TAB + TAB + "res = this.h.invoke(this, method, argValues)" + SEM + LINE;
                }else{
                    tmpInvoke += TAB + TAB + TAB + "this.h.invoke(this, method, argValues)" + SEM + LINE;
                }
                tmpInvoke += TAB + TAB + "} catch(Throwable e){" + LINE;
                tmpInvoke += TAB + TAB + TAB + "e.printStackTrace()" + SEM + LINE;
                tmpInvoke += TAB + TAB + "}" + LINE;
                if(!returnType.getSimpleName().equalsIgnoreCase("void")){
                    tmpInvoke += TAB + TAB + "return (" + returnType.getSimpleName() + ")res" + SEM + LINE;
                }
                tmpInvoke += TAB + "}" + LINE;
                //加入当前方法调用
                methodInvokes += tmpInvoke;
            }
        }
        String sourceCode = packCode + importSource + classDef + propsDef + constructor + methodInvokes + "}";
        return sourceCode;
    }

}
