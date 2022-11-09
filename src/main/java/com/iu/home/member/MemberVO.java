package com.iu.home.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails,OAuth2User{
	
	@NotBlank(message ="이이이잉 ID 얼른써줘잉!!!")
	private String id;
	@NotBlank
//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\W)(?=\\\\S+$).{6,12}")
	@NotBlank
	private String pw;
	private String pwCheck;
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	@Range(max = 150 ,min = 0)
	private int age;
	@Past()
	private Date birth;
	
	private	boolean enabled;
	
	private List<RoleVO> roleVOs;
	
	//==========Social Login===========
	//kakao,naver,google
	private String social;
	
	//OAuth2User , Token등 정보 저장
	private Map<String, Object> attributes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {//?-어떤것(any) extends-상속 GrantedAuthority-이것을 상속받은 애들은 된다
		// <? super T> ?-어떤것 T-T나 부모를 
		//<? super T> T나 T의 부모타입을 허용하겠다 라는 뜻
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add( new SimpleGrantedAuthority(roleVO.getRoleName()) );
			
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pw;
//		return this.getPw();
		
	}

	@Override
	public String getUsername() {
		// ID 반환
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		//계정의 만료여부
		//true : 만료 안됨;
		//fasle: 만료 됨, 로그인 불가 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부
		// true : 계정이 잠기지 않음
		// fasle : 계정이 잠김, 로그인 불가
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호 만료 여부
		// true  : 만료 안됨
		// fasle : 만료 됨, 로그인 안됨
		return true;
	}
	
	//isEnabled
	//계정 사용 여부
	// true  : 계정 활성화(계정 사용 가능)
	// false : 계정 비활성화(계정 사용 불가, 로그인 불가)
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return this.attributes;
	}

}
