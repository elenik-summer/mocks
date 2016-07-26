package org.mocks.actions;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import javax.servlet.http.HttpServletRequest;

public class GetClientIdFromBodyAction extends AbstractAction {

    private HttpServletRequest request;

    private String getAttributeByName(String attributeName) {
        try {
            JsonParser jParser = new JsonFactory().createParser(request.getReader());
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                if (jParser.nextToken() == JsonToken.FIELD_NAME) {
                    String fieldname = jParser.getCurrentName();
                    if (attributeName.equalsIgnoreCase(fieldname)) {
                        if (jParser.nextToken() == JsonToken.VALUE_STRING) {
                            return jParser.getValueAsString();
                        }
                    }
                }
            }
            jParser.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String execute() {

        String clientId = getAttributeByName("Client");

        String resultName = getResultName(clientId);

        return resultName == null ? DEFAULT : resultName;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
