package dibujo.elemento;

import dibujo.Canvas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Linha extends Elemento {

    private final int endingY;
    private final int endingX;
    private final int startingX;
    private final int startingY;

    public Linha(String line) {
        super(line);
        Matcher matcher = Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            this.startingX = Integer.parseInt(matcher.group(1));
            this.startingY = Integer.parseInt(matcher.group(2));
            this.endingX = Integer.parseInt(matcher.group(3));
            this.endingY = Integer.parseInt(matcher.group(4));
        } else {
            throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
        }
    }

    public void desenha(Canvas canvas) {
        super.desenha(canvas);
        canvas.createNewLine(startingX, startingY, endingX, endingY);
    }
}
