package com.naverAPI.BTL;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

// Controller에서 BO Class 이용 할 수 있도록 Servlet.xml에 NaverLoginBO Class에 대한 빈 설정 추가
// /<!-- NaverLoginBO Class에 대한 Bean설정 추가 -->
//	<beans:bean id="naverLoginBO" class="com.naverAPI.BTL.NaverLoginBO" />
@Controller
public class LoginController {

	//NaverLoginBO
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	//로그인 첫 화면 요청
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(Model model, HttpSession session) {
		
		//네이버 아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http://localhost:8282/BTL/logined
		
		System.out.println("네이버: "+ naverAuthUrl);
		
		//네이버
		model.addAttribute("url", naverAuthUrl);
		
		//생성한 인증 URL을 View로 전달
		return "login";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
   
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		//access token의 정보를 불러오기 위하여 모델에 담아준다.
		model.addAttribute("token",oauthToken );
		
        /* 네이버 로그인 성공 페이지 View 호출 */
		return "naverSuccess";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		   
		return "redirect:/";
	}
	

	
}
