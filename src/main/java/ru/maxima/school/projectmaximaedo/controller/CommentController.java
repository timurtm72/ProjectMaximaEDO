package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.CommentDto;
import ru.maxima.school.projectmaximaedo.serviceImpl.CommentServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentServiceImpl commentService;
    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    /**
     * Считывание списка комментариев
     * @return списка комментариев или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<CommentDto>> getComments() {
        List<CommentDto> comments = commentService.getAll();
        if(comments == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список комментариев пуст");
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Считывание комментария
     * @param id
     * @return комментарий или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long id) {
        CommentDto commentDto = commentService.getById(id);
        if(commentDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Комментарий с " + id + " не найден");
        }
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    /**
     * Обновление комментария
     * @param id
     * @param commentDto
     * @return статус обновления комментария
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateComment(@PathVariable("id") Long id,
                                                  @Valid @RequestBody CommentDto commentDto) {
        if (commentService.update(commentDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Комментарий с идентификатором " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление комментария прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание комментария
     * @param commentDto
     * @return статус создания комментария
     */
    @PostMapping()
    public ResponseEntity<Response> createComment(@Valid @RequestBody CommentDto commentDto) {
        if (commentService.create(commentDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании комментария");
        }
        return ResponseEntity.accepted().body(new Response("Создание комментария прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление комментария
     * @param id
     * @return статус удаления комментария
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteComment(@PathVariable("id") Long id) {

        if (commentService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление комментария прошло успешно", LocalDateTime.now()));
    }

}
