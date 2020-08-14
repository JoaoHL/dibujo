package dibujo;

public class Elemento {

    public Elemento(String line, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }
    }
}
