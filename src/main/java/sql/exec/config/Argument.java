package sql.exec.config;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Argument {
    private ArgumentName name;
    private String value;

    public Argument(ArgumentName name) {
        this.name = name;
    }

    public Argument(ArgumentName name, String value) {
        this.name = name;
        this.value = value;
    }

    public ArgumentName getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Argument{");
        sb.append("name=").append(name);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
