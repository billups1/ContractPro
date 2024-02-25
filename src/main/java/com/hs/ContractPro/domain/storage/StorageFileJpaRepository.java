package com.hs.ContractPro.domain.storage;

import com.hs.ContractPro.domain.externalParty.ExternalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageFileJpaRepository extends JpaRepository<StorageFile, Long> {
}
