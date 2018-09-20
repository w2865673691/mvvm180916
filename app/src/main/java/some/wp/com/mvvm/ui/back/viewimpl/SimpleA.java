package some.wp.com.mvvm.ui.back.viewimpl;

import javax.inject.Inject;

public class SimpleA {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name="test1";

    SimpleB simpleB;
    @Inject
    public SimpleA( SimpleB simpleB) {
        this.simpleB=simpleB;
    }

    @Override
    public String toString() {
        return super.toString()+getName();
    }
}
