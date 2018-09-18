package some.wp.com.mvvm.common.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import some.wp.com.mvvm.view.BaseAdapter;

public class BindRecyclerView {

    @BindingAdapter({"recyclerAapter"})
    public static void recyclerAapter(RecyclerView recyclerView, BaseAdapter baseAdapter) {
        recyclerView.setAdapter(baseAdapter);
    }
}
