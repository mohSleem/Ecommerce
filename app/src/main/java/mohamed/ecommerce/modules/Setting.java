package mohamed.ecommerce.modules;

public class Setting {
    private int mButtonIcon ;
    private String mSettingTitle;

    public int getmButtonIcon() {
        return mButtonIcon;
    }

    public void setmButtonIcon(int mButtonIcon) {
        this.mButtonIcon = mButtonIcon;
    }

    public String getmSettingTitle() {
        return mSettingTitle;
    }

    public void setmSettingTitle(String mSettingTitle) {
        this.mSettingTitle = mSettingTitle;
    }

    public Setting(int mButtonIcon, String mSettingTitle) {

        this.mButtonIcon = mButtonIcon;
        this.mSettingTitle = mSettingTitle;
    }
}
