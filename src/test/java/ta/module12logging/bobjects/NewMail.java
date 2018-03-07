package ta.module12logging.bobjects;

public class NewMail{

    private String newMail;
    private String newMail2;
    private String testTheme;
    private String mailAdress;

    public NewMail(String newMail, String newMail2, String testTheme) {
        this.newMail = newMail;
        this.newMail2 = newMail2;
        this.testTheme = testTheme;
    }

    public String getNewMail() {
        return newMail;
    }

    public String getNewMail2() {
        return newMail2;
    }

    public String getTestTheme() {
        return testTheme;
    }

}
