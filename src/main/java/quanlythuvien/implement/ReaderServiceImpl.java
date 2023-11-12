package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.model.Reader;
import quanlythuvien.repository.ReaderRepository;
import quanlythuvien.service.ReaderService;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader findReaderById(String id) {
        return readerRepository.findById(id).get();
    }

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Reader deleteReader(String id) {
        readerRepository.deleteById(id);
        return null;
    }


}
