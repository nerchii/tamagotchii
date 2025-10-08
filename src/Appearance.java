import java.io.Serializable;

public class Appearance implements Serializable {
    protected static final String[] defaultLook = new String[]{
            " /\\_/\\",
            "( o.o )",
            " > ^ <"
    };

    protected static final String[] sleepy = new String[]{
            " /\\_/\\",
            "( -.- ) zZ",
            " >   <"
    };

    protected static final String[] happy = new String[]{
            " /\\_/\\",
            "( ^.^ )  ⭒☆⋆",
            " >   <"
    };

    protected static final String[] playful = new String[]{
            " /\\_/\\",
            "( ^.^ )ノ :33",
            " > O <"
    };

    protected static final String[] sad = new String[]{
            " /\\_/\\",
            "( T.T )",
            " >   <"
    };

    protected static final String[] eating = new String[]{
            " /\\_/\\",
            "( ^.^ )",
            " > = < "
    };


    protected String[] currentLook = defaultLook;

    public String[] getCurrentLook() {
        return currentLook;
    }
    public void setCurrentLook(String[] currentLook) {
        this.currentLook = currentLook;
    }
}