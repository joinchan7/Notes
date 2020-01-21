/**
 * 静态代理 设计模式
 * 公共接口:
 * 1.真实角色
 * 2.代理角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        new weddingCompany(new You()).happyMarry();
    }
}

interface Marry {
    void happyMarry();
}

class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("happyMarry");
    }
}

class weddingCompany implements Marry {
    private Marry target;

    public weddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("ready");
    }

    private void after() {
        System.out.println("after");
    }
}
