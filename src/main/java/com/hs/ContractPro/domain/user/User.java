package com.hs.ContractPro.domain.user;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.contract.ContractTeam;
import com.hs.ContractPro.domain.contract.ContractUser;
import com.hs.ContractPro.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true)
    private String loginId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "team")
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ContractUser> contractUsers;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ROLE role;

    public enum ROLE {
        ADMIN, MEMBER
    }

}
