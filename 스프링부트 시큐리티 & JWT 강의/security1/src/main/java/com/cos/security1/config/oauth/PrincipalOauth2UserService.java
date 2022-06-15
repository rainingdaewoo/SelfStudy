package com.cos.security1.config.oauth;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.FacebookUserInfo;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.config.oauth.provider.NaverUserInfo;
import com.cos.security1.config.oauth.provider.OAuth2userInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@NoArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * 설명: 구글로부터 받은 userRequest 데이터에 대한 후처리 함수
     * @param userRequest
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest:" + userRequest.getClientRegistration()); // registrationId로 어떤 Oauth로 로그인 했는지 확인가능.
        System.out.println("userRequest:" + userRequest.getAccessToken());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
        System.out.println("userRequest:" + oAuth2User.getAttributes());


        OAuth2userInfo oAuth2userInfo = null;
        if( userRequest.getClientRegistration().getRegistrationId().equals("google") ) {

            System.out.println("구글 로그인 요청");
            oAuth2userInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        } else if ( userRequest.getClientRegistration().getRegistrationId().equals("facebook") ) {

            System.out.println("페이스북 로그인 요청");
            oAuth2userInfo = new FacebookUserInfo(oAuth2User.getAttributes());

        } else if ( userRequest.getClientRegistration().getRegistrationId().equals("naver") ) {

            System.out.println("네이버 로그인 요청");
            oAuth2userInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));

        } else {
            System.out.println("우리 사이트는 구글, 페이스북, 네이버 로그인만 지원합니다.");
        }

        String provider = oAuth2userInfo.getProvider();                             // google
        String providerId = oAuth2userInfo.getProviderId();                         // 숫자 ex) 12345566
        String username = provider + "_" + providerId;                              // google_123456
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oAuth2userInfo.getEmail();
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        if( userEntity == null ) {

            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
            System.out.println("회원 가입이 완료되었습니다.");
        } else {
            System.out.println("이미 회원가입 되어 있습니다.");
        }

        // 회원 가입 강제 진행행
       return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
