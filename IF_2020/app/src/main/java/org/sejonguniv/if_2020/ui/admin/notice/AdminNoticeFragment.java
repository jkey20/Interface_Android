package org.sejonguniv.if_2020.ui.admin.notice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {
    AdminNoticeAdapter adapter = new AdminNoticeAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);

        binding.setViewModel(viewModel);
        binding.setNoticeList(viewModel.titleList);

        startProgressBar();

        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());

        binding.noticeRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new onAdapterItemClickListener());
        viewModel.getNoticeList(dialog);

        binding.saveButton.setOnClickListener(new onClickListener());

        return binding.getRoot();
    }

    private class onRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            binding.swipeRefreshlayout.setRefreshing(false);
            dialog.show();
            viewModel.titleList.clear();
            viewModel.getNoticeList(dialog);
        }
    }

    private class onAdapterItemClickListener implements AdminNoticeAdapter.OnItemClickListener {
        @Override
        public void onItemClick(View v, int position) {
            if (v.getId() == R.id.delete_button) {
                viewModel.deleteNotice(position);
            } else {
                Notice editNotice = new Notice();
                editNotice.setId(position);
                editNotice.setTitle(binding.titleEdittext.getText().toString());
                editNotice.setContent(binding.contentEdittext.getText().toString());

                viewModel.editNotice(position, editNotice);
            }
        }
    }

    private class onClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Notice notice = new Notice();
            notice.setTitle(binding.titleEdittext.getText().toString());
            notice.setContent(binding.contentEdittext.getText().toString());
            viewModel.saveNotice(notice);
            dialog.show();
            viewModel.getNoticeList(dialog);
            adapter.notifyDataSetChanged();
        }
    }

}