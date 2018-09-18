package some.wp.com.mvvm.common.binding;

import android.databinding.BindingAdapter;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import some.wp.com.mvvm.view.BaseAdapter;

public class BindRecyclerView {

    @BindingAdapter({"recyclerAapter"})
    public static void recyclerAapter(XRecyclerView recyclerView, BaseAdapter baseAdapter) {
        recyclerView.setAdapter(baseAdapter);
    }
}
