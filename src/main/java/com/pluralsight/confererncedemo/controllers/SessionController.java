package com.pluralsight.confererncedemo.controllers;

import com.pluralsight.confererncedemo.models.Session;
import com.pluralsight.confererncedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @PostMapping
    public Session create(@RequestBody Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingsession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingsession, "session_id");
        return sessionRepository.saveAndFlush(existingsession);
    }


}
