package com.hs.ContractPro.domain.dto.contract;

import com.hs.ContractPro.domain.contract.Contract;
import com.hs.ContractPro.domain.contract.Category;
import com.hs.ContractPro.domain.contract.ContractCategory;
import com.hs.ContractPro.domain.storage.StorageFile;

import java.util.ArrayList;
import java.util.List;

public record ContractCreateView (
        Long id,
        String name,
        String contentSummary,
        String categoryName,
        List<ContractCreateStorageFileView> contractCreateStorageFileViews,
        Contract.CONTRACT_METHOD contractMethod
) {
    public static ContractCreateView from(Contract contract) {

        List<StorageFile> storageFiles = contract.getStorageFiles();
        ArrayList<ContractCreateStorageFileView> contractCreateStorageFileViews = new ArrayList<>();
        for (StorageFile storageFile : storageFiles) {
            contractCreateStorageFileViews.add(ContractCreateStorageFileView.from(storageFile));
        }

        return new ContractCreateView(
                contract.getId(),
                contract.getName(),
                contract.getContentSummary(),
                contract.getContractCategory().getCategory().getCategoryName(),
                contractCreateStorageFileViews,
                contract.getContractMethod()
        );

    }

}
