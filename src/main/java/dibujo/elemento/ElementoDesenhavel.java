package dibujo.elemento;

public abstract class ElementoDesenhavel extends Elemento {

    public ElementoDesenhavel(String line, String regex) {
        super(line, regex);
    }

    public void desenha(Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new element.");
        }
    }
}
