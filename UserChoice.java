package easybank;

public enum UserChoice {
    WITHDRAW("1"), DEPOSIT("2");
    private final String option;

    UserChoice(String opt) {
        this.option = opt;
    }

    public String getOption() {
        return this.option;
    }
}
