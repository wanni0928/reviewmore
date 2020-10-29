package com.side.revicemore.controller;

import com.side.revicemore.controller.form.GalleryForm;
import com.side.revicemore.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;

    @GetMapping(value = "/galleries/new")
    public String createForm(Model model) {
        model.addAttribute("form", new GalleryForm());
        return "gallery/createForm";
    }

    @PostMapping(value = "/galleries/new")
    public String create(GalleryForm form) {
        Long memberId = 56L;
        galleryService.write(memberId, form.getTitle(), form.getContent());
        return "redirect:/galleries";
    }

    @GetMapping(value = "/galleries")
    public String list(Model model) {
        model.addAttribute("galleries", galleryService.findGalleries());
        return "gallery/galleryList";
    }
}
