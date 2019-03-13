package org.sj.testdemo.zxing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.sj.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TestFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int pageNo;

    private List<String> listData;
    private CommonAdapter<String> listAdapter;

    private Unbinder unbinder;
    private int type;

    public static TestFragment getInstance(int type) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_test, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        Toast.makeText(getActivity(), "78", Toast.LENGTH_SHORT).show();
        refreshLayout.setDisableContentWhenLoading(true);
        refreshLayout.setDisableContentWhenRefresh(true);
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        type = getArguments().getInt("type", 0);
        listData = new ArrayList<>();
        listData.addAll(getData(0));
        listAdapter = new CommonAdapter<String>(getActivity(), R.layout.item_test, listData) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_item_text, s);
            }
        };
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(listAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                listData.clear();
                pageNo = 0;
                listData.addAll(getData(pageNo));
                listAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                listData.addAll(getData(++pageNo));
                listAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);
            }
        });
//        refreshLayout.autoRefresh();
    }

    private List<String> getData(int pageNo){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("这是第" + type + "页,第" + (30*pageNo + i) + "条");
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
