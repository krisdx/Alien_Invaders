package contracts;

import java.awt.*;

public interface Updatable {
    void tick();

    void render(Graphics graphics);
}