package partuzabook.usuarioUI;

public enum Sex {
    MALE("1"),
    FEMALE("0");

    private String key;

    Sex(String key) {
        this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
}
