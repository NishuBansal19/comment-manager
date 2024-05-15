package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.ActionInput;
import com.intuit.commentmanager.dto.outbound.ActionCount;
import com.intuit.commentmanager.dto.outbound.BasicProfileDetails;
import com.intuit.commentmanager.service.ViewerActionService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Action api to like/dislike a comment")
    public ResponseEntity<String> setAction(@RequestBody ActionInput actionInput) {
        String res = viewerActionService.setAction(actionInput);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Utility api to list all the comments")
    public List<ActionInput> getAllActions() {
        return viewerActionService.getAllActions();
    }

    @GetMapping("/comment/{commentId}/profiles")
    @Operation(summary = "Get the paginated list of profile using comment id")
    public Page<BasicProfileDetails> getAllProfileActedOnComment(@PathVariable long commentId,
                                                                 @RequestParam(required = true) String action) {
        return viewerActionService.getAllProfileActedOnComment(commentId, action);
    }

    @GetMapping("/comment/{commentId}/count")
    @Operation(summary = "Get the like/dislike count using comment id")
    public ActionCount getActionCount(@PathVariable long commentId) {
        return viewerActionService.getActionCount(commentId);
    }
}
