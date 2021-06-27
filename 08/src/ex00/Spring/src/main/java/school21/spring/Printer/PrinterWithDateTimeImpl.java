package school21.spring.Printer;

import school21.spring.Renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{
    Renderer renderer;

    @Override
    public void print(String str) {
        renderer.render(LocalDateTime.now() + " " + str);
    }

    public PrinterWithDateTimeImpl(Renderer renderer)
    {
        this.renderer = renderer;
    }
}
