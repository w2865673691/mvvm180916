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
import some.wp.com.mvvm.view.main.viewimpl.ViewImplTab0;

public class FragmentTab0 extends BaseFragment {
    @Inject
    ViewImplTab0 viewImplTab0;

    @Inject
    public FragmentTab0() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewImplTab0.fragment(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewImplTab0.bind(R.layout.frag_tab0,container);
        return viewImplTab0.root();
    }
}
