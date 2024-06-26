package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quanlythuvien.model.Book;
import quanlythuvien.model.Category;
import quanlythuvien.model.Reader;
import quanlythuvien.service.ReaderService;

import java.util.List;

@Controller
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/readers")
    public String reader(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "reader";
    }

    @GetMapping("/readers/new")
    public String createReader(Model model) {
        Reader reader = new Reader();
        model.addAttribute("newReader", reader);
        return "create_reader";
    }

    @PostMapping("/readers/new")
    public String saveReader(@ModelAttribute("newReader") Reader reader) {
        Reader reader1 = new Reader();
        return mapReader(reader, reader1);
    }

    @GetMapping("/readers/update/{id}")
    public String updateReader(Model model, @PathVariable String id) {
        Reader reader = readerService.findReaderById(id);
        model.addAttribute("updateReader", reader);
        return "update_reader";
    }

    @PostMapping("/readers/update/{id}")
    public String saveUpdateReader(@ModelAttribute("updateReader") Reader reader, @PathVariable String id) {
        Reader reader1 = readerService.findReaderById(id);
        return mapReader(reader, reader1);
    }

    private String mapReader(@ModelAttribute("updateReader") Reader reader, Reader reader1) {
        reader1.setId(reader.getId());
        reader1.setName(reader.getName());
        reader1.setPhone(reader.getPhone());
        reader1.setEmail(reader.getEmail());
        reader1.setAddress(reader.getAddress());
        readerService.saveReader(reader1);
        return "redirect:/readers";
    }

    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable String id) {
        readerService.deleteReader(id);
        return "redirect:/readers";
    }

    @PostMapping("/readers/search/{content}")
    @ResponseBody
    public List<Reader> searchReader(@PathVariable String content) {
        if (content.equals(null) || content.equals("")) {
            return readerService.getAllReaders();
        }
        else return readerService.searchReader(content);
    }

    @PostMapping("/readers/check/{id}")
    @ResponseBody
    public String checkReader(@PathVariable String id) {
        Reader reader = readerService.checkReader(id);
        if (reader!=null) return "true";
        else return "false";
    }

}
