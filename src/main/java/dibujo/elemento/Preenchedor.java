package dibujo.elemento;

import dibujo.Canvas;
import dibujo.Position;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preenchedor extends ElementoDesenhavel {

    private final int startingX;
    private final int startingY;
    private final String colorCharacter;

    public Preenchedor(String line) {
        super(line, "^B (\\d+) (\\d+) (\\w+)$");
        if (matcher.find()) {
            this.startingX = Integer.parseInt(matcher.group(1));
            this.startingY = Integer.parseInt(matcher.group(2));
            this.colorCharacter = matcher.group(3);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
        }
    }

    public void desenha(Canvas canvas) {
        super.desenha(canvas);
        Position startingPositionToFill = new Position(startingX, startingY);
        canvas.fill(startingPositionToFill, colorCharacter);
    }
}
