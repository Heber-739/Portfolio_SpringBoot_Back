package com.portfolio.Controller;

import com.portfolio.DTO.UsserDTO;
import com.portfolio.Entity.Image;
import com.portfolio.Entity.Usser;
import com.portfolio.Security.Message;
import com.portfolio.Service.ImageService;
import com.portfolio.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://heberportfolio.web.app", "*"})
@RestController
@RequestMapping("/user")
public class UsserController {

    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;

    @GetMapping("/get")
    public Usser getDefaultUsser() {
        return userService.findUsser("Heber739");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{id}")
    public Usser getUsser(@RequestParam("id") String id) {
        return userService.findUsser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<Usser> getAllUsser() {
        return userService.getAllUssers();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createUsser(@RequestBody UsserDTO usser) {
        if (userService.existsByGithub(usser.getGithub())) {
            return new ResponseEntity<>(new Message("El github ingresado ya esta registrado"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByLinkedin(usser.getLinkedin())) {
            return new ResponseEntity<>(new Message("El linkedin ingresado ya esta registrado"), HttpStatus.BAD_REQUEST);
        }
        Image img = new Image(usser.getImg().getName(), usser.getImg().getType(), usser.getImg().getBlobImg());
        imageService.save(img);

        Usser user = new Usser(usser.getUsername(), usser.getName(), usser.getSurname(), usser.getAge(), usser.getDescription(), usser.getLinkedin(), usser.getGithub());
        user.setImg(img);
        userService.saveUsser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}