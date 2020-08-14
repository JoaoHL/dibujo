package dibujo.elemento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Elemento {

    protected final Matcher matcher;

    public Elemento(String line, String regex) {
        matcher = Pattern.compile(regex).matcher(line);
    }
}
