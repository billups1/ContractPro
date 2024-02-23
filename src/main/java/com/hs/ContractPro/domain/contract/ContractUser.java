package com.hs.ContractPro.domain.contract;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "externalParties")
public class ContractUser extends BaseTimeEntity {

    @EmbeddedId
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
