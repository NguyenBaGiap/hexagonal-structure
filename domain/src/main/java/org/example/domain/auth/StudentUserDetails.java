package org.example.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.domain.student.models.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class StudentUserDetails implements UserDetails {

    private Student student;
    private Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public static StudentUserDetails apply(Student student){
        List<GrantedAuthority> authorities = student.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return StudentUserDetails.builder()
                .student(student)
                .authorities(authorities)
                .build();
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return student.getEmail();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
