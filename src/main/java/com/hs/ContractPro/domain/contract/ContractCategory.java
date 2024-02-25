package com.hs.ContractPro.domain.contract;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.team.Team;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "ContractCategories")
public class ContractCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "contractCategory")
    private Contract contract;

    @OneToOne(mappedBy = "contractCategory")
    private Category category;

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
