package dibujo.elemento;

import dibujo.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Elemento {

    protected final Matcher matcher;

    public Elemento(String line, String regex) {
        matcher = Pattern.compile(regex).matcher(line);
    }

    public void desenha(Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }
    }
}
