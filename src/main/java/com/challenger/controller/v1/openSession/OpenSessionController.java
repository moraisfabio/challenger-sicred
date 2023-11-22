package com.challenger.controller.v1.openSession;

import com.challenger.services.session.SessionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("v1/session")
@RestController
public class OpenSessionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    private final SessionServiceImpl sessionService;
    @PostMapping("/{idSchedule}/new-session-votes")

    public ResponseEntity newSessionVotes(@PathVariable("idSchedule") Integer idSchedule){
        logger.info("Starting session to votes " + idSchedule);
        sessionService.starSessionVotes(idSchedule, LocalDateTime.now().plusSeconds(sessionService.getTimeSessionDefault()));
        logger.info("session to votes started, this session will expire in " + sessionService.getTimeSessionDefault() + " seconds");
        return ResponseEntity.ok().build();
    }

}
