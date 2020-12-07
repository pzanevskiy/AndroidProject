package com.example.rentalcar.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.entity.Invoice;
import com.example.rentalcar.repository.InvoiceRepository;

import java.util.List;

public class InvoiceViewModel extends AndroidViewModel {
    private InvoiceRepository invoiceRepository;
    public InvoiceViewModel(@NonNull Application application) {
        super(application);
        invoiceRepository=new InvoiceRepository(application);
    }

    public LiveData<List<Invoice>> getInvoices(String status, int id){
        return invoiceRepository.getInvoices(status, id);
    }
    public void addInvoice(Invoice invoice){
        invoiceRepository.addInvoice(invoice);
    }
    public void deleteInvoice(Invoice invoice){
        invoiceRepository.deleteInvoice(invoice);
    }
    public void updateInvoice(Invoice invoice){
        invoiceRepository.updInvoice(invoice);
    }
}
