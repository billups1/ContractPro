package com.hs.ContractPro.domain.team;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.contract.ContractTeam;
import com.hs.ContractPro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "teams")
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> members;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<ContractTeam> contractTeams;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // 상위본부

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Team> teams;



}
