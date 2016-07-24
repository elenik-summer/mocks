package com.voya.actions;

public class GetPartyIdFromQueryAction extends AbstractAction {

    public String execute() {
        String partyId = getRequest().getParameter("partyId");

        String resultName = getResultName(partyId);

        return resultName == null ? DEFAULT : resultName;
    }

}