package gasipan.confing;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AdminLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
    public AdminLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
}
