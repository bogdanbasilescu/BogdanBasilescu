package ro.bbasilescu.bogdanbasilescu.presentation.usecases.skills;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.view.AbsBaseFragmentView;

public class SkillsFragment extends AbsBaseFragmentView<SkillsContract.SkillsPresenter> implements SkillsContract.SkillsView<SkillsContract.SkillsPresenter> {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_skills;
    }

    @Override
    public SkillsContract.SkillsPresenter makePresenter() {
        return new SkillsPresenterImpl(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void displaySkills() {

    }

    @Override
    public void showToast() {

    }
}
