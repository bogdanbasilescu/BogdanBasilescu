package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.view.AbsBaseFragmentView;

public class AboutFragment extends AbsBaseFragmentView<AboutContract.AboutPresenter> implements AboutContract.AboutView<AboutContract.AboutPresenter> {

    @BindView(R.id.tv_about_bio)
    public TextView tvAboutBio;
    @BindView(R.id.tv_about_obj_value)
    public TextView tvAboutObjValue;
    @BindView(R.id.iv_profile_photo)
    public CircleImageView ivProfilePhoto;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ivProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onProfilePhotoClicked();
                getPresenter().getToast();
            }
        });

        return view;
    }

    @Override
    public void displayUser(User user) {
        tvAboutBio.setText(user.getDescription());
        tvAboutObjValue.setText(user.getObjective());
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "Profile photo clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayProfilePhotoImgPicker() {
        // TODO: create Intent for Image Picker
    }
}
