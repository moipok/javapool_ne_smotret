package school21.spring.Renderer;

import school21.spring.PreProcessor.PreProcessor;

public class RendererStandardImpl implements Renderer{
    PreProcessor preprocessor;

    @Override
    public void render(String str) {
        System.out.println(preprocessor.preproc(str));
    }

    public RendererStandardImpl(PreProcessor a){
        preprocessor = a;
    }
}
