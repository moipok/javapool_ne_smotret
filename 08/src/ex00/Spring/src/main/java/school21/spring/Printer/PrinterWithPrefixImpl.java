package school21.spring.Printer;

import school21.spring.Renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer{
    Renderer renderer;
    private String prefix = null;

    @Override
    public void print(String str) {
        renderer.render(((prefix != null) ? prefix + " " + str : str));
    }
    public void setPrefix(String pref)
    {
        this.prefix = pref;
    }

    public PrinterWithPrefixImpl(Renderer renderer)
    {
        this.renderer = renderer;
    }

}
