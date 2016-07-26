package org.mocks.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.inject.Inject;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AbstractAction extends ActionSupport implements ServletRequestAware {

    public static final String DEFAULT = "default";

    private HttpServletRequest request;
    private String packageName;
    private String actionName;

    @Inject
    private Configuration config;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getResultName(String resultName) {
        PackageConfig packageConfig = config.getPackageConfig(getPackageName());
        if (packageConfig != null) {
            ActionConfig actionConfig = packageConfig.getActionConfigs().get(getActionName());
            if (actionConfig != null) {
                Map<String, ResultConfig> params = actionConfig.getResults();
                if (params.get(resultName) != null) {
                    return resultName;
                }
            }
        }
        return null;
    }
}
