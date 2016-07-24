package com.voya.actions;

public class GetPlanIdFromQueryAction extends AbstractAction {

    public String execute() {
        String planId = getRequest().getParameter("planId");

        String resultName = getResultName(planId);

        return resultName == null ? DEFAULT : resultName;
    }

}
