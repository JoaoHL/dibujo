package dibujo.elemento;

import dibujo.Canvas;

public abstract class Elemento {

    public Elemento(String line) {}

    public void desenha(Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }
    }
}
