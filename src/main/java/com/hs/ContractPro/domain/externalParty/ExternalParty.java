package com.hs.ContractPro.domain.externalParty;

import com.hs.ContractPro.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "externalParties")
public class ExternalParty extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TYPE type;

    private String companyRegistrationNumber;

    public enum TYPE { // 법인격 종류 : 사람, 회사, 개인사업자
        PERSON, COMPANY, SOLE_PROPRIETOR
    }

}
