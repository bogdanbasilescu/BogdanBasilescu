package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.view.AbsBaseFragmentView;

public class AboutFragment extends AbsBaseFragmentView<AboutContract.AboutPresenter> implements AboutContract.AboutView<AboutContract.AboutPresenter> {

    @BindView(R.id.tv_about_bio)
    public TextView tvAboutBio;
    @BindView(R.id.tv_about_obj_value)
    public TextView tvAboutObjValue;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutContract.AboutPresenter makePresenter() {
        return new AboutPresenterImpl(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().getUser();
    }

    @Override
    public void displayUser(User user) {
        tvAboutBio.setText(user.getDescription());
        tvAboutObjValue.setText(user.getObjective());
    }

    @Override
    public void showMessage(int message) {
        // Display Snackbar/Toast if needed
    }

}
