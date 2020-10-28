package com.side.revicemore.controller;

import com.side.revicemore.domain.Gallery;
import com.side.revicemore.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;

    @GetMapping(value = "/galleries/new")
    public String createForm(Model model) {
        model.addAttribute("form", new Gallery());
        return "gallery/createForm";
    }

//    @PostMapping(value = "/galleries/new")
//    public String create() {
//
//    }
}
