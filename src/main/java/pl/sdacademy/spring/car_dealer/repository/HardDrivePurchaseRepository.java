package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.qualifier.HardDriveStorage;

import java.util.List;

@Repository
@HardDriveStorage
public class HardDrivePurchaseRepository extends AbstractHardDriveRepository<Purchase> implements PurchaseRepository {
    private final String repositoryLocation;

   HardDrivePurchaseRepository(@Value("${repository.purchase.hardDrive.fileLocation}") String repositoryLocation) {
        this.repositoryLocation = repositoryLocation;
    }

    @Override
    public List<Purchase> getAll() {
        return loadAllElements();
    }

    @Override
    public Purchase add(Purchase purchase) {
        List<Purchase> purchases = loadAllElements();
        Long newPurchaseId = getNextId(purchases);
        purchase.setId(newPurchaseId);
        purchases.add(purchase);
        saveAllElements(purchases);
        return purchase;
    }

    @Override
    protected String getRepositoryLocation() {
        return repositoryLocation;
    }

    public void initialize() {
        System.out.println("Initializing repository");
    }

    public void cleanUp() {
        System.out.println("Cleaning up repository");
    }
}
