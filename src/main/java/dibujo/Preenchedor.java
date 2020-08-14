package dibujo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preenchedor extends Elemento {
    public Preenchedor(String line, Canvas canvas) {
        super(line, canvas);
    }

    public static void preencheEspacoComCor(String line, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
        }

        Matcher matcher = Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher(line);
        if (matcher.find()) {
            int startingX = Integer.parseInt(matcher.group(1));
            int startingY = Integer.parseInt(matcher.group(2));
            String colorCharacter = matcher.group(3);

            Position startingPositionToFill = new Position(startingX, startingY);
            canvas.fill(startingPositionToFill, colorCharacter);
        } else {
            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
        }
    }
}
