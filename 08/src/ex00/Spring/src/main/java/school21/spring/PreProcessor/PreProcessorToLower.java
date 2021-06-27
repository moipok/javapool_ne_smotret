package school21.spring.PreProcessor;

import java.util.Locale;

public class PreProcessorToLower implements PreProcessor{
    @Override
    public String preproc(String str) {
        return str.toLowerCase(Locale.ROOT);
    }
}
