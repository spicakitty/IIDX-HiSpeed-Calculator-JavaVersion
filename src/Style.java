import java.util.ArrayList;

public class Style {
    private String name;
    private int screenlimit;
    private ArrayList<HiSpeed> availableHS;

    public Style (String name, int screenlimit, ArrayList<HiSpeed> availableHS) {
        this.name=name;
        this.screenlimit=screenlimit;
        this.availableHS=availableHS;
    }

    public String getName() {
        return this.name;
    }

    public int getScreenlimit(Settings setting) {
        // if (setting.isIgnoreScreenLimit()){
        //     return 50;
        // }
        // return this.screenlimit;
        return setting.isIgnoreScreenLimit() ? 50 : this.screenlimit;
    }

    public ArrayList<HiSpeed> getAvailableHS() {
        return this.availableHS;
    }
}
