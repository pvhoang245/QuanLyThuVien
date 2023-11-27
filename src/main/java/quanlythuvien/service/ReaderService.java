package quanlythuvien.service;

import quanlythuvien.model.Category;
import quanlythuvien.model.Reader;

import java.util.List;

public interface ReaderService {
    Reader saveReader(Reader reader);
    Reader findReaderById(String id);
    List<Reader> getAllReaders();
    Reader deleteReader(String id);
    List<Reader> searchReader(String content);

    Reader checkReader(String id);
}
