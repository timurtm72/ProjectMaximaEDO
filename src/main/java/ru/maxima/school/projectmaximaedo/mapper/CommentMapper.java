package ru.maxima.school.projectmaximaedo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.school.projectmaximaedo.dto.CommentDto;
import ru.maxima.school.projectmaximaedo.model.Comment;
import ru.maxima.school.projectmaximaedo.utils.MapperUtil;

@Component
public class CommentMapper  implements IMapper<Comment, CommentDto> {
    private final MapperUtil mapperUtil;
    @Autowired
    public CommentMapper(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }
    @Override
    public CommentDto toDto(Comment comment) {
        return mapperUtil.getMapper().map(comment, CommentDto.class);
    }
    @Override
    public Comment toEntity(CommentDto commentDto) {
        return mapperUtil.getMapper().map(commentDto, Comment.class);
    }
}
