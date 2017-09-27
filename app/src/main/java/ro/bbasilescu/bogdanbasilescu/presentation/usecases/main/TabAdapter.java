package ro.bbasilescu.bogdanbasilescu.presentation.usecases.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ro.bbasilescu.bogdanbasilescu.R;
import ro.bbasilescu.bogdanbasilescu.presentation.usecases.about.AboutFragment;
import ro.bbasilescu.bogdanbasilescu.presentation.usecases.experience.ExperienceFragment;
import ro.bbasilescu.bogdanbasilescu.presentation.usecases.hobbies.HobbiesFragment;
import ro.bbasilescu.bogdanbasilescu.presentation.usecases.projects.ProjectsFragment;
import ro.bbasilescu.bogdanbasilescu.presentation.usecases.skills.SkillsFragment;

/**
 * Class for TabAdapter - FragmentPagerAdapter
 */
public class TabAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public enum Tab {

        // Define Tab items for the given constructor
        About(R.drawable.ic_profile_tab, R.string.title_about_fragment),
        Experience(R.drawable.ic_medium, R.string.title_experience_fragment),
        Projects(R.drawable.ic_medium, R.string.title_projects_fragment),
        Skills(R.drawable.ic_medium, R.string.title_skills_fragment),
        Hobbies(R.drawable.ic_medium, R.string.title_hobbies_fragment);

        // Define enum attributes
        public int tabIconRes;
        public int tabTitleRes;

        // Define constructor for Tab enum type
        Tab(int tabIconRes, int tabTitleRes) {
            this.tabIconRes = tabIconRes;
            this.tabTitleRes = tabTitleRes;
        }
    }

    private Context context;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    /**
     * Constructor
     *
     * @param fm      FragmentManager for FragmentPagerAdapter
     * @param context Context
     */
    public TabAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * Set Tab layout
     *
     * @param tabLayout The layout for the tab
     */
    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    /**
     * Get fragment depending on the selected item
     *
     * @param position The position in the adapter
     * @return The instance of the selected fragment
     */
    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position >= Tab.values().length)
            return null;

        switch (Tab.values()[position]) {
            case About:
                return new AboutFragment();
            case Experience:
                return new ExperienceFragment();
            case Projects:
                return new ProjectsFragment();
            case Skills:
                return new SkillsFragment();
            case Hobbies:
                return new HobbiesFragment();
        }
        return null;
    }

    /**
     * Get the number of items in the Tab
     *
     * @return the number of Tab items
     */
    @Override
    public int getCount() {
        return Tab.values().length;
    }

    /**
     * Get the title of the page depending on the selected fragment
     *
     * @param position The position in the adapter
     * @return The title of the selected fragment for the page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(Tab.values()[position].tabTitleRes);
    }

    /**
     * Set the layout for a tab item depending on the position of it in the adapter
     *
     * @param position The position in the adapter
     * @return The view that is inflated with the custom layout
     */
    public View getCustomView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_header_view, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(Tab.values()[position].tabIconRes);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(getPageTitle(position));
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * Change color for the title view from the Tab's layout when the page
     * corresponding to the Tab has been selected
     *
     * @param position The position in the adapter
     */
    @Override
    public void onPageSelected(int position) {
        int unselectedColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
        int selectedColor = ContextCompat.getColor(context, R.color.colorTextIcons);

        for (int i = 0; i < Tab.values().length; i++) {
            boolean selected = i == position;
            TabLayout.Tab selectedTab = tabLayout.getTabAt(i);
            if (selectedTab != null) {
                View view = selectedTab.getCustomView();
                if (view != null) {
                    ImageView icon = (ImageView) view.findViewById(R.id.icon);
                    icon.setColorFilter(selected ? selectedColor : unselectedColor);
                    TextView text = (TextView) view.findViewById(R.id.title);
                    text.setTextColor(selected ? selectedColor : unselectedColor);
                    if (toolbar != null && selected) {
                        toolbar.setTitle(selectedTab.getText());
                    }
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
