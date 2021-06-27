package school21.spring.PreProcessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor{
    @Override
    public String preproc(String str) {
        return str.toUpperCase(Locale.ROOT);
    }
}
