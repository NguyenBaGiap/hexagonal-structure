package org.example.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.persistence.comon.BaseEntity;
import org.example.persistence.entity.constants.EnumRole;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROLE")
public class RoleEntity extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", length = 100)
    private EnumRole name;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
