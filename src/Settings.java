public class Settings {
    private int singleGN;
    private int doubleGN;
    private boolean displayOverLimit;
    private boolean ignoreScreenLimit;

    public Settings() {
        this.singleGN=298;
        this.doubleGN=335;
        this.displayOverLimit=true;
        this.ignoreScreenLimit=false;
    }

    public int getSingleGN() {
        return this.singleGN;
    }

    public int getDoubleGN() {
        return this.doubleGN;
    }

    public boolean isDisplayOverLimit() {
        return this.displayOverLimit;
    }

    public boolean isIgnoreScreenLimit() {
        return this.ignoreScreenLimit;
    }

    public void setSingleGN(int greenNumber) {
        this.singleGN=greenNumber;
    }

    public void setDoubleGN(int greenNumber) {
        this.doubleGN=greenNumber;
    }

    public void toggleDisplayOverLimit() {
        this.displayOverLimit=!this.displayOverLimit;
    }

    public void toggleIgnoreScreenLimit() {
        this.ignoreScreenLimit=!this.ignoreScreenLimit;
    }
}
