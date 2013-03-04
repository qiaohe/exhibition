package cn.mobiledaily.web.common;

import com.sun.faces.renderkit.html_basic.TextRenderer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;

public class InputRender extends TextRenderer {
    @Override
    protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String value = (String) component.getAttributes().get("autofocus");
        if (value != null) {
            writer.writeAttribute("autofocus", "autofocus", "autofocus");
        }
        super.getEndTextToRender(context, component, currentValue);
    }
}
