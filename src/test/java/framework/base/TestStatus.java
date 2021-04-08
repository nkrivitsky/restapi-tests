package framework.base;

public enum TestStatus {
    PASSED("passed"),
    FAILED("failed");

    String name;

    TestStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
