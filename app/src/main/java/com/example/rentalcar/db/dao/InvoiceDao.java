package com.example.rentalcar.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.rentalcar.db.entity.Invoice;
import com.example.rentalcar.db.entity.Order;

import java.util.List;

@Dao
public interface InvoiceDao {
    @Query("SELECT * FROM invoices WHERE status=:status AND user_id=:id")
    LiveData<List<Invoice>> getInvoices(String status, int id);

    @Insert
    void addInvoice(Invoice invoice);

    @Delete
    void deleteInvoice(Invoice invoice);

    @Update
    void updateInvoice(Invoice invoice);
}
