package dibujo.elemento;

public class Retangulo extends ElementoDesenhavel {

    private final int upperLeftCornerX;
    private final int upperLeftCornerY;
    private final int lowerRightCornerX;
    private final int lowerRightCornerY;

    public Retangulo(String line) {
        super(line, "^R (\\d+) (\\d+) (\\d+) (\\d+)$");
        if (matcher.find()) {
            this.upperLeftCornerX = Integer.parseInt(matcher.group(1));
            this.upperLeftCornerY = Integer.parseInt(matcher.group(2));
            this.lowerRightCornerX = Integer.parseInt(matcher.group(3));
            this.lowerRightCornerY = Integer.parseInt(matcher.group(4));
        } else {
            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
        }
    }

    public void desenha(Canvas canvas) {
        super.desenha(canvas);
        canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
    }
}
