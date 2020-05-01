## 1.代理模式
### 1.1动态代理（底层都是反射）
- 1.动态代理和静态代理的角色一样
- 2.动态代理的代理类是动态生成的，不是我们直接写好的
- 3.动态代理分为两大类：基于接口的动态代理，基于类的动态代理<br><br>
步骤：
- 1.创建ProxyInvocationHandler类并且继承InvocationHandler接口
- 2.生成代理类.
- 3.创建被代理的接口（set方法）
```java
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 处理代理实例并返回结果
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        prient(method.getName());
        Object invoke = method.invoke(target, args);
        return invoke;
    }
    public void prient(String msg){
        System.out.println("打印了"+msg);
    }
}
```
- 4.客户端通过动态代理访问角色
```java
public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host=new Host();
        ProxyInvocationHandler pih=new ProxyInvocationHandler();
        pih.setTarget(host);
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}
```

### 1.2静态代理
角色分析：
- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理人对象的人

代码步骤：
- 接口
```java
public interface Rent {
    void rent();
}
```
- 真实角色
```java
public class Host implements Rent {

    @Override
    public void rent() {
        System.out.println("房东出租房子");
    }
}
```
- 代理角色
```java
public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        hetong();
        free();
    }
    //看房
    public void seeHouse(){
        System.out.println("看房子");
    }
    //合同
    public void  hetong(){
        System.out.println("签合同");
    }
    //中介费
    public void free(){
        System.out.println("手中介费");
    }
}
```
- 客户端访问代理角色
```java
public class Client {
    public static void main(String[] args) {
        //房东要出租房子
        Host host=new Host();
        //代理，中介帮房东出租房子。但是，代理一般会有一些附属操作
        Proxy proxy=new Proxy(host);
        //不用面对房东，直接找中介租房即可
        proxy.rent();
    }
}
```

## 2 AOP
```text
在软件业，AOP为Aspect Oriented Programming的缩写，
意为：面向切面编程，通过预编译方式和运行期间动态代理实现程序功能的统一维护的一种技术。
AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，
是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离
，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

```
