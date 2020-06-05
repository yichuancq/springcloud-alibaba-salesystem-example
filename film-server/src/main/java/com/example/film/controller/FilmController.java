package com.example.film.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @calss name FilmController
 * @description:FilmController
 * @author: yichuan
 * @create time: 2020/06/06 03:59
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class FilmController {

    /***
     *不允许访问
     */
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String test() {
        log.info("test");
        return "test";
    }

    /***
     *
     */
    @GetMapping("/show")
    public String show() {
        log.info("show");
        return "show";
    }

}
