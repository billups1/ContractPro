package com.hs.ContractPro.domain.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @Column(name = "rt_key")
    private String key; // loginId

    @Column(name = "rt_value")
    private String value; // refreshToken

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

}
