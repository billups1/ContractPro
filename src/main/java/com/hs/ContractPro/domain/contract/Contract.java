package com.hs.ContractPro.domain.contract;

import com.hs.ContractPro.common.BaseTimeEntity;
import com.hs.ContractPro.domain.storage.StorageFile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity(name = "contracts")
public class Contract extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content_summary")
    private String contentSummary;

    @OneToOne
    @JoinColumn(name = "contract_category_id")
    private ContractCategory contractCategory;

    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private List<ContractUser> contractUsers;
    @OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    private List<ContractTeam> contractTeams;

    @Column(name = "contract_date")
    private LocalDateTime contractSealDate;
    @Column(name = "contract_effective_date")
    private LocalDateTime contractEffectiveDate;
    @Column(name = "contract_end_date")
    private LocalDateTime contractEndDate;

    @OneToMany(mappedBy = "contract",fetch = FetchType.LAZY)
    private List<StorageFile> storageFiles;

    private CONTRACT_METHOD contractMethod;

    public enum CONTRACT_METHOD {
        PAPER, ELECTRIC
    }

    public void setContractCategory(ContractCategory contractCategory) {
        this.contractCategory = contractCategory;
    }
}
