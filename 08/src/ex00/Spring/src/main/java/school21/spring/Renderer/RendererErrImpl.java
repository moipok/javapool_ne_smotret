package school21.spring.Renderer;

import school21.spring.PreProcessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    PreProcessor preprocessor;

    @Override
    public void render(String str) {
        System.err.println(preprocessor.preproc(str));
    }

    public RendererErrImpl(PreProcessor a){
        preprocessor = a;
    }
}
