package dibujo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Retangulo implements Elemento {

    private int upperLeftCornerX;
    private int upperLeftCornerY;
    private int lowerRightCornerX;
    private int lowerRightCornerY;

    public Retangulo(int upperLeftCornerX, int upperLeftCornerY, int lowerRightCornerX, int lowerRightCornerY, Canvas canvas) {
        this.upperLeftCornerX = upperLeftCornerX;
        this.upperLeftCornerY = upperLeftCornerY;
        this.lowerRightCornerX = lowerRightCornerX;
        this.lowerRightCornerY = lowerRightCornerY;
    }

    @Override
    public Elemento cria(String line) {
        Matcher matcher = Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher(line);
        if (matcher.find()) {
            int upperLeftCornerX = Integer.parseInt(matcher.group(1));
            int upperLeftCornerY = Integer.parseInt(matcher.group(2));
            int lowerRightCornerX = Integer.parseInt(matcher.group(3));
            int lowerRightCornerY = Integer.parseInt(matcher.group(4));

            return new Retangulo(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
            //canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
    }
}
