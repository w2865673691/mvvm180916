package some.wp.com.mvvm.view.main.fragements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.wplib.view.BaseFragment;

import javax.inject.Inject;

import some.wp.com.mvvm.R;
import some.wp.com.mvvm.view.main.viewimpl.ViewImplTab2;

public class FragmentTab2 extends BaseFragment {
    @Inject
    ViewImplTab2 viewImplTab2;

    @Inject
    public FragmentTab2() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewImplTab2.fragment(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewImplTab2.bind(R.layout.frag_tab2,container);
        return viewImplTab2.root();
    }
}
