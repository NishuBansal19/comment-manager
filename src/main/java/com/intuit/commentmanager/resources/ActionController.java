package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.ActionInput;
import com.intuit.commentmanager.dto.outbound.BasicProfileDetails;
import com.intuit.commentmanager.service.ViewerActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ViewerActionService viewerActionService;

    @PostMapping
    public ResponseEntity<String> setAction(@RequestBody ActionInput actionInput) {
        String res = viewerActionService.setAction(actionInput);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    public List<ActionInput> getAllActions() {
        return viewerActionService.getAllActions();
    }

    @GetMapping("/profiles/{commentId}")
    public Page<BasicProfileDetails> getAllProfileActedOnComment(@PathVariable long commentId,
                                                                 @RequestParam String action) {
        return viewerActionService.getAllProfileActedOnComment(commentId, action);
    }
}
