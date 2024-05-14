package com.intuit.commentmanager.cache;

import com.intuit.commentmanager.dto.outbound.ActionCount;
import com.intuit.commentmanager.entity.ViewerAction;
import com.intuit.commentmanager.enums.ActionType;
import com.intuit.commentmanager.repository.ViewerActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static javax.management.timer.Timer.ONE_DAY;

@Component
public class ActionCacheService {

    public static final String CACHE_NAME = "comment_action_count";

    private static final Logger logger = LoggerFactory.getLogger(ActionCacheService.class);

    @Autowired
    public ViewerActionRepository viewerActionRepository;

    @Autowired
    public CacheManager cacheManager;

    @Cacheable(value = CACHE_NAME, key = "#commentId")
    public ActionCount getActionCount(long commentId) {
        return getCount(commentId);
    }

    private ActionCount getCount(long commentId) {
        List<ViewerAction> likeActions =  viewerActionRepository.findReplyCommentByParentCommentId(commentId, ActionType.LIKE.name());
        List<ViewerAction> disLikeActions =  viewerActionRepository.findReplyCommentByParentCommentId(commentId, ActionType.DISLIKE.name());
        return ActionCount.builder().likes(likeActions.size()).dislikes(disLikeActions.size()).build();
    }

    @CachePut(value = CACHE_NAME, key = "#commentId")
    public ActionCount updateActionCount(long commentId) {
        logger.debug("Updating like/dislike cache for comment id : {}", commentId);
        return getCount(commentId);
    }

    @Scheduled(fixedRate = ONE_DAY)
    public void clearCache() {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        if(cache != null) {
            cache.clear();
        }
    }

}
