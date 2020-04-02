package com.pluralsight.confererncedemo.controllers;

import com.pluralsight.confererncedemo.models.Speaker;
import com.pluralsight.confererncedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingspeaker = speakerRepository.getOne(id);

        //copies to other and makes existing empty
        BeanUtils.copyProperties(speaker,existingspeaker,"speaker_id");

        //the existing is replaced by the new speaker
        return speakerRepository.saveAndFlush(existingspeaker);
    }
}
