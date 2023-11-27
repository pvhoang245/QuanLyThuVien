package quanlythuvien.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReaderCountDto {
    private Reader reader;
    private long totalBorrow;
}
