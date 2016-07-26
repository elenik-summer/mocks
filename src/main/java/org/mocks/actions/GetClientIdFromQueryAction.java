package org.mocks.actions;

public class GetClientIdFromQueryAction extends AbstractAction {

    public String execute() {
        String clientId = getRequest().getParameter("clientId");

        String resultName = getResultName(clientId);

        return resultName == null ? DEFAULT : resultName;
    }

}