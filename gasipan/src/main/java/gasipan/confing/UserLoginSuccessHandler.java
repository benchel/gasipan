package gasipan.confing;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * (의역) 
 * ExceptionTranslationFilter에 의해 세션에 저장했을지도 모르는 DefaultSavedRequest를 이용할 수 있는 authentication success strategy
 * 
 * 인증을 가로챘거나 인증이 필요한 경우, 요청 데이터는 인증 프로세스를 시작하기 전에 원래 목적지를 기록하며
 * 동일한 URL 발생으로 리다이렉트되었을 때 해당 요청이 재구성되도록 허용한다.
 * -> 다른 클래스를 상속받도록 하자...리다이렉트 요청 너무많다고 에러남 흑흑...
 */
public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
    public UserLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
}
