package service;

import model.Bill;
import java.util.ArrayList;
import java.util.List;

public class BillingService {

    // In-memory list for demo/testing (will be replaced by DAO)
    private List<Bill> billList = new ArrayList<>();

    /**
     * Generates a new bill for a patient.
     */
    public void generateBill(Bill bill) {
        if (bill == null) {
            throw new IllegalArgumentException("Bill details cannot be null.");
        }
        bill.setPaymentStatus("Unpaid");
        billList.add(bill);
    }

    /**
     * Process payment for a bill by its bill ID.
     */
    public void payBill(String billId) {
        boolean found = false;

        for (Bill bill : billList) {
            // String.valueOf() handles potential primitive int IDs safely
            if (String.valueOf(bill.getBillId()).equals(billId)) {
                bill.setPaymentStatus("Paid");
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Bill with ID " + billId + " not found.");
        }
    }

    /**
     * Retrieves all bill records.
     */
    public List<Bill> getAllBills() {
        return billList;
    }
}