package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.ActionInput;
import com.intuit.commentmanager.dto.outbound.ActionCount;
import com.intuit.commentmanager.dto.outbound.BasicProfileDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ViewerActionService {

    public String setAction(ActionInput actionInput);

    public void deleteActionById(long id);

    public List<ActionInput> getAllActions();

    public Page<BasicProfileDetails> getAllProfileActedOnComment(long commentId, String action);

    public ActionCount getActionCount(long commentId);
}
