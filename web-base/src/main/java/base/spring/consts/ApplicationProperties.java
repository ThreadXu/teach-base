package base.spring.consts;

public enum ApplicationProperties {

    Thymeleaf_Cache("application.template.cache", "true");

    private final String key;
    private final String defaultValue;

    ApplicationProperties(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

}
