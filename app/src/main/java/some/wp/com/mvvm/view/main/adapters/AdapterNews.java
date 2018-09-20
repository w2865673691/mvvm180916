package some.wp.com.mvvm.view.main.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import some.wp.com.mvvm.BR;
import some.wp.com.mvvm.R;
import some.wp.com.mvvm.taskmodel.beans.NewsBean;
import com.architecture.wplib.view.adapter.BaseAdapter;
import com.architecture.wplib.view.adapter.BaseViewHolder;


public class AdapterNews extends BaseAdapter<BaseViewHolder> {

    public AdapterNews(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.adapter, this);
        binding.executePendingBindings(); //防止闪烁
    }


    /**
     * 点赞
     */
    public void clickDianZan(NewsBean simpleNewsBean, int position) {
//        ToastUtils.show(mContext, "点赞成功 position=" + position);
    }
}
