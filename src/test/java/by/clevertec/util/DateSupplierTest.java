package by.clevertec.util;

import by.clevertec.helper.DateSupplier;

import java.time.LocalDateTime;

public class DateSupplierTest implements DateSupplier {

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.parse("2024-09-12T10:15:30");
    }
}
