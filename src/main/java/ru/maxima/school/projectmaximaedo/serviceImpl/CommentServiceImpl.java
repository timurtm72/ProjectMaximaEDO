package ru.maxima.school.projectmaximaedo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.school.projectmaximaedo.dto.CommentDto;
import ru.maxima.school.projectmaximaedo.mapper.CommentMapper;
import ru.maxima.school.projectmaximaedo.model.Comment;
import ru.maxima.school.projectmaximaedo.repository.CommentRepository;
import ru.maxima.school.projectmaximaedo.service.CommentService;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MapperUtil mapperUtil;
    private final CommentMapper commentMapper;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, MapperUtil mapperUtil, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.mapperUtil = mapperUtil;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getAll() {
        List<Comment> comments = commentRepository.findAllByIsRemovedIsFalseOrderByIdAsc();
        if(comments == null || comments.size() == 0 ){
            return null;
        }
        return mapperUtil.mapList(comments,CommentDto.class);
    }

    @Override
    public Boolean exists(Long id) {
        return commentRepository.existsByIdIsRemovedIsFalse(id);
    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findCommentByIdIsRemovedIsFalse(id).orElse(null);
        return comment != null ? commentMapper.toDto(comment) : null;
    }
    @Override
    public Boolean create(CommentDto commentDto) {
       Comment comment =  commentMapper.toEntity(commentDto);
        if(comment != null){
            comment.setCreatedAt(LocalDateTime.now());
            comment.setRemoved(false);
            commentRepository.save(comment);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(CommentDto commentDto, Long id) {
        if(commentDto == null){
            return true;
        }
        commentDto.setId(id);
        Comment comment =  commentMapper.toEntity(commentDto);
        Comment readComment = commentRepository.findCommentByIdIsRemovedIsFalse(id).orElse(null);
        if(readComment != null){
            comment.setRemoved(readComment.getRemoved());
            commentRepository.save(comment);
            return false;
        }
        return true;
    }

    @Override
    public Boolean safeDelete(Long id) {
        Comment comment = commentRepository.findCommentByIdIsRemovedIsFalse(id).orElse(null);
        if(comment != null){
            comment.setRemoved(true);
            commentRepository.save(comment);
            return false;
        }
        return true;
    }
}
