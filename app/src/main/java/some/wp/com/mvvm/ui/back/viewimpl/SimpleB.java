package some.wp.com.mvvm.ui.back.viewimpl;

import com.architecture.wplib.common.LogActs;

import javax.inject.Inject;

public class SimpleB {

    @Inject
    public SimpleB() {
    }

    public void dosome(){
        LogActs.d("SimpleB-->dosome");
    }
}
