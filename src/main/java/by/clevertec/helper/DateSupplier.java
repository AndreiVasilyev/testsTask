package by.clevertec.helper;

import java.time.LocalDateTime;

public interface DateSupplier {
    default LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
