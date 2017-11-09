package com.sky.health.fragment.common;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.sky.health.R;
import com.sky.health.adapter.common.PullZoomRecyclerAdapter;
import com.sky.health.adapter.common.RecyclerViewHeaderAdapter;
import com.sky.health.base.BaseFragment;
import com.sky.health.bean.common.DetectionBean;
import com.sky.health.interfaces.RequestReturnListener;
import com.sky.health.view.common.PullToZoomRecyclerViewEx;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOME on 2016/11/15.
 */
public class PullToZoomFragment extends BaseFragment implements RequestReturnListener<DetectionBean>
{
    private PullToZoomRecyclerViewEx listView;
    private PullZoomRecyclerAdapter pullZoomRecyclerAdapter;
    public static PullToZoomFragment newInstance() {
        return new PullToZoomFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.pull_zoom_recyclerview, container, false);
        initView(view);

        return view;
    }

    private void initView(View view)
    {
        pullZoomRecyclerAdapter = new PullZoomRecyclerAdapter(getActivity(),R.layout.detection_gridview_item);
        pullZoomRecyclerAdapter.setDetectionBeanList(requestData());
        pullZoomRecyclerAdapter.setRequestReturnListener(this);
        listView = (PullToZoomRecyclerViewEx) view.findViewById(R.id.recyclerview);

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                return pullZoomRecyclerAdapter.getItemViewType(position) == RecyclerViewHeaderAdapter.INT_TYPE_HEADER ? 4 : 1;
            }
        });
        listView.setAdapterAndLayoutManager(pullZoomRecyclerAdapter,manager);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, mScreenHeight/5*3);
        listView.setHeaderLayoutParams(localObject);
    }

    /**
     * 获取数据
     */
    private List<DetectionBean> requestData()
    {
        List<DetectionBean> detectionBeanList = new LinkedList<>();
        DetectionBean detectionBean = null;
        for (int i = 0; i < 7;i++)
        {
            detectionBean = new DetectionBean();
            switch (i)
            {
                case 0:
                    detectionBean.setDetectionName("只能检测");
                    detectionBean.setImgResId(R.mipmap.detection_pressed);
                    break;
                case 1:
                    detectionBean.setDetectionName("人工检测");
                    detectionBean.setImgResId(R.mipmap.found_pressed);
                    break;
                case 2:
                    detectionBean.setDetectionName("更多检测");
                    detectionBean.setImgResId(R.mipmap.home_pressed);
                    break;
                default:
                    detectionBean.setImgResId(-1);
                    break;
            }
            detectionBeanList.add(detectionBean);
            detectionBean = null;
        }
        return detectionBeanList;
    }

    @Override
    public void returnResult(DetectionBean result) {
        if(result != null)
        {
            Toast.makeText(getActivity(),result.getDetectionName(),Toast.LENGTH_SHORT).show();
        }
    }
}
