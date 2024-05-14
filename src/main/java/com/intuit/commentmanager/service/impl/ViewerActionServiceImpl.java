package com.intuit.commentmanager.service.impl;


import com.intuit.commentmanager.cache.ActionCacheService;
import com.intuit.commentmanager.dto.inbound.ActionInput;
import com.intuit.commentmanager.dto.outbound.ActionCount;
import com.intuit.commentmanager.dto.outbound.BasicProfileDetails;
import com.intuit.commentmanager.entity.Comment;
import com.intuit.commentmanager.entity.Post;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.entity.ViewerAction;
import com.intuit.commentmanager.enums.ActionType;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import com.intuit.commentmanager.mappers.ActionMapper;
import com.intuit.commentmanager.mappers.ProfileMapper;
import com.intuit.commentmanager.repository.CommentRepository;
import com.intuit.commentmanager.repository.ProfileRepository;
import com.intuit.commentmanager.repository.ViewerActionRepository;
import com.intuit.commentmanager.service.ViewerActionService;
import com.intuit.commentmanager.validators.ActionValidator;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO - Make it extensible for any other actions

@Service
public class ViewerActionServiceImpl implements ViewerActionService {

    @Autowired
    private ViewerActionRepository viewerActionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ActionValidator actionValidator;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ActionCacheService actionCacheService;

    @Override
    public String setAction(ActionInput actionInput) {
        ActionType actionType = ActionType.lookUpType(actionInput.getActionType());
        Optional<Profile> profileOptional = profileRepository.findById(actionInput.getProfileId());
        if(profileOptional.isEmpty()) {
            throw new InvalidInputException("Profile is not valid");
        }
        Profile profile = profileOptional.get();
        Optional<Comment> commentOptional = commentRepository.findById(actionInput.getCommentId());
        if(commentOptional.isEmpty()) {
            throw new InvalidInputException("Comment is not valid");
        }
        Comment comment = commentOptional.get();
        String res;
        List<ViewerAction> existingActions = viewerActionRepository.findByCommentAndActionBy(comment, profile);

        if (!CollectionUtils.isEmpty(existingActions)) {
            ViewerAction viewerAction = existingActions.get(0);
            if(actionType.name().equalsIgnoreCase(viewerAction.getActionType()) && !actionInput.isActionState()) {
                deleteActionById(viewerAction.getId());
                res = actionType.name().toLowerCase() + " removed successfully";
            } else if (!actionType.name().equalsIgnoreCase(viewerAction.getActionType()) && actionInput.isActionState()) {
                viewerAction.setActionType(actionType.name());
                viewerActionRepository.save(viewerAction);
                res = actionType.name().toLowerCase() + " successfully";
            } else {
                res = "No action taken";
            }
        } else {
            ViewerAction viewerAction = actionMapper.mapInboundToEntity(actionInput);
            viewerAction.setActionBy(profile);
            viewerAction.setComment(comment);
            viewerActionRepository.save(viewerAction);
            res = actionType.name().toLowerCase() + " successfully";
        }
        actionCacheService.updateActionCount(actionInput.getCommentId());
        return res;
    }

    @Override
    public Page<BasicProfileDetails> getAllProfileActedOnComment(long commentId, String action) {
        ActionType actionType = ActionType.lookUpType(action);
        Page<ViewerAction> viewerActions = viewerActionRepository.findReplyCommentByParentCommentId(commentId,
                actionType.name(), PageRequest.of(0, 5, Sort.Direction.DESC, "createdDt"));
        return viewerActions.map(viewerAction-> profileMapper.mapProfileToBasicProfile(viewerAction.getActionBy()));
    }

    @Override
    public ActionCount getActionCount(long commentId) {
        return actionCacheService.getActionCount(commentId);
    }

    @Override
    public void deleteActionById(long id) {
        viewerActionRepository.deleteById(id);
    }

    @Override
    public List<ActionInput> getAllActions() {
        List<ViewerAction> actions = viewerActionRepository.findAll();
        return actions.parallelStream()
                .map(action -> actionMapper.mapEntityToInbound(action)).collect(Collectors.toList());
    }
}
