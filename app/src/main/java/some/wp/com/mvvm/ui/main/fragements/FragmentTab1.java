package some.wp.com.mvvm.ui.main.fragements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.architecture.wplib.ui.BaseFragment;

import javax.inject.Inject;

import some.wp.com.mvvm.R;
import some.wp.com.mvvm.ui.main.viewimpl.ViewImplTab1;

public class FragmentTab1 extends BaseFragment {
    @Inject
    ViewImplTab1 viewImplTab1;

    @Inject
    public FragmentTab1() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewImplTab1.fragment(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewImplTab1.bind(R.layout.frag_tab1,container);
        return viewImplTab1.root();
    }
}
