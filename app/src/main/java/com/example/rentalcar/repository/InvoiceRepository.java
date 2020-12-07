package com.example.rentalcar.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.rentalcar.db.dao.InvoiceDao;
import com.example.rentalcar.db.database.RentalDatabase;
import com.example.rentalcar.db.entity.Invoice;
import com.example.rentalcar.db.entity.User;

import java.util.List;

public class InvoiceRepository {
    private RentalDatabase rentalDatabase;
    private InvoiceDao invoiceDao;

    public InvoiceRepository(Application application){
        rentalDatabase=RentalDatabase.getInstance(application);
        invoiceDao=rentalDatabase.invoiceDao();
    }

    public LiveData<List<Invoice>> getInvoices(String status, int id){
       return invoiceDao.getInvoices(status, id);
    }

    public void addInvoice(Invoice invoice){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            invoiceDao.addInvoice(invoice);
        });
    }

    public void deleteInvoice(Invoice invoice){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            invoiceDao.deleteInvoice(invoice);
        });
    }
    public void updInvoice(Invoice invoice){
        RentalDatabase.databaseWriteExecutor.execute(()->{
            invoiceDao.updateInvoice(invoice);
        });
    }
}
