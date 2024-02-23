package com.hs.ContractPro.domain;

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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "bio")
    private String bio;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ENTITY_TYPE type;

    private String companyRegistrationNumber;

    public enum ENTITY_TYPE { // 법인격 종류 : 사람, 회사, 개인사업자
        PERSON, COMPANY, SOLE_PROPRIETOR
    }

}
