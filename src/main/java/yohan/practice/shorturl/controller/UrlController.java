package yohan.practice.shorturl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yohan.practice.shorturl.service.ShortService;


@RequiredArgsConstructor
@RequestMapping
@Controller
public class UrlController {

    private final ShortService service;

    @ResponseBody
    @PostMapping("/short/request")
    public ResponseEntity<String> requestShortUrl(String originalUrl) {
        String shortUrl = service.makeShortUrl(originalUrl);

        return new ResponseEntity<>(shortUrl, HttpStatus.OK);
    }

    @GetMapping("/{path}")
    public String go(@PathVariable String path) {
        String originalUrl = service.requestShortUrl(path);

        return "redirect:%s".formatted(originalUrl);
    }

}
