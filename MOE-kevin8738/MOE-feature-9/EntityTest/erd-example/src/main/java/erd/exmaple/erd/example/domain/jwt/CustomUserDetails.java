<<<<<<< HEAD
//package erd.exmaple.erd.example.domain.jwt;
=======
package erd.exmaple.erd.example.domain.jwt;//package erd.exmaple.erd.example.domain.jwt;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
//
//import erd.exmaple.erd.example.domain.UserEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final UserEntity user;
//    public CustomUserDetails(UserEntity user) {
//        this.user = user;
//    }
//
//     //현재 user의 role을 반환 (ex. "ROLE_ADMIN" / "ROLE_USER" 등)
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                // 앞에 "ROLE_" 접두사 필수 !
//                return "ROLE_" + user.getNickname();
//            }
//        });
//
//        return collection;
//    }
//
//    // user의 비밀번호 반환
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//     //user의 username 반환
//    @Override
//    public String getUsername() {
//        return user.getPhoneNumber();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}