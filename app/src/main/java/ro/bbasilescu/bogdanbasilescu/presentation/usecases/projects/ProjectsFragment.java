package ro.bbasilescu.bogdanbasilescu.presentation.usecases.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.view.AbsBaseFragmentView;

public class ProjectsFragment extends AbsBaseFragmentView<ProjectsContract.ProjectsPresenter> implements ProjectsContract.ProjectsView<ProjectsContract.ProjectsPresenter> {

    @BindView(R.id.tv_project_title)
    public TextView tvProjectTitle;
    @BindView(R.id.tv_project_date)
    public TextView tvProjectDate;
    @BindView(R.id.iv_project_photo)
    public ImageView ivProjectPhoto;
    @BindView(R.id.item_project)
    public LinearLayout itemProject;

    // TODO: Add RecyclerView and RecyclerAdapter & RecyclerAdapter.ViewHolder for projects

    @Override
    public int getLayoutId() {
        return R.layout.fragment_projects;
    }

    @Override
    public ProjectsContract.ProjectsPresenter makePresenter() {
        return new ProjectsPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);


        return view;
    }

    @Override
    public void showProjects() {

    }

    @Override
    public void showProjectDetails() {

    }

    @Override
    public void showToast() {

    }
}
