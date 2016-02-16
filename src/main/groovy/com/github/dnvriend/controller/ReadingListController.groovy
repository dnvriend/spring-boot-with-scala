package com.github.dnvriend.controller

import com.github.dnvriend.repository.book.Book
import com.github.dnvriend.repository.book.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/")
class ReadingListController {
    @Autowired
    private BookRepository bookRepository

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String root() {
        "home"
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = bookRepository.findByReader(reader)
        if(readingList) {
            model.addAttribute("books", readingList)
        }
        "readinglist"
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader)
        bookRepository.save(book)
        "redirect:/{reader}"
    }

    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String home() {
        "home"
    }

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String hello() {
        "hello"
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        "login"
    }
}
